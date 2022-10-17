package me.ilker.dota2compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.internal.schedulers.IoScheduler
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.toObservable
import io.reactivex.rxjava3.kotlin.zipWith
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.ilker.dota2compose.model.domain.Team
import me.ilker.dota2compose.model.domain.TeamHero
import me.ilker.dota2compose.model.domain.TeamPlayer
import me.ilker.dota2compose.presenter.HeroesState
import me.ilker.dota2compose.presenter.TeamState
import me.ilker.dota2compose.presenter.TeamsState
import javax.inject.Inject
import me.ilker.dota2compose.repository.LeaguesRepository
import me.ilker.dota2compose.repository.PlayersRepository
import me.ilker.dota2compose.service.NetworkService

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: NetworkService,
    private val leaguesRepository: LeaguesRepository,
    private val playersRepository: PlayersRepository
) : ViewModel() {
    private var subscription = Disposable.disposed()

    private val _heroesState = MutableStateFlow<HeroesState>(HeroesState.Empty)
    private val _teamsState = MutableStateFlow<TeamsState>(TeamsState.Empty)
    private val _teamState = MutableStateFlow<TeamState>(TeamState.Empty)

    val heroesState: StateFlow<HeroesState>
        get() = _heroesState

    val teamsState: StateFlow<TeamsState>
        get() = _teamsState

    val teamState: StateFlow<TeamState>
        get() = _teamState

    fun getHeroes() {
        _heroesState.value = HeroesState.Loading

        viewModelScope.launch {
            try {
                val heroes = apiService.getHeroes().map { it.toDomain() }
                _heroesState.value = HeroesState.Loaded(heroes)
            } catch (e: Exception) {
                _heroesState.value = HeroesState.Error(e)
            }
        }
    }

    fun getTeams() {
        _teamsState.value = TeamsState.Loading

        viewModelScope.launch {
            try {
                val teams = apiService
                    .getTeams()
                    .map { teamResponse ->
                        teamResponse.toDomain()
                    }.sortedByDescending { team ->
                        team.wins
                    }
                _teamsState.value = TeamsState.Loaded(teams)
            } catch (e: Exception) {
                _teamsState.value = TeamsState.Error(e)
            }
        }
    }

    fun getTeamData(team: Team) {
        if (subscription.isDisposed) {
            _teamState.value = TeamState.Loading

            viewModelScope.launch {
                val teamID = team.teamId.toString()
                val teamPlayers = apiService.getTeamPlayers(teamID).toObservable()
                val teamHeroes = apiService.getTeamHeroes(teamID).toObservable()

                val players1: MutableList<TeamPlayer> = mutableListOf()
                val heroes1: MutableList<TeamHero> = mutableListOf()

                subscription = teamPlayers
                    .zipWith(teamHeroes)
                    .subscribeOn(IoScheduler())
                    .observeOn(IoScheduler())
                    .map { responsePair ->
                        val player = responsePair.first.toDomain()
                        val hero = responsePair.second.toDomain()

                        players1.add(player)
                        heroes1.add(hero)
                    }
                    .subscribeBy(
                        onNext = {
                            _teamState.value = TeamState.Loaded(
                                team = team.copy(
                                    players = players1,
                                    heroes = heroes1
                                )
                            )
                        }
                    )
            }
        }

        subscription.dispose()
    }
}
