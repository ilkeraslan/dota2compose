package me.ilker.dota2compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.ilker.dota2compose.network.NetworkService
import me.ilker.dota2compose.presenter.HeroesState
import me.ilker.dota2compose.presenter.TeamsState

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: NetworkService
) : ViewModel() {

    fun getHeroes() {
        viewModelScope.launch {
            try {
                val heroes = apiService.getHeroes().map { it.toDomain() }
                _heroState.value = HeroesState.Loaded(heroes)
            } catch (e: Exception) {
                _heroState.value = HeroesState.Error(e)
            }
        }
    }

    fun getTeams() {
        viewModelScope.launch {
            try {
                val teams = apiService
                    .getTeams()
                    .map { teamResponse ->
                        teamResponse.toDomain()
                    }.sortedByDescending { team ->
                        team.wins
                    }
                _teamState.value = TeamsState.Loaded(teams)
            } catch (e: Exception) {
                _teamState.value = TeamsState.Error(e)
            }
        }
    }

    private val _heroState = MutableStateFlow<HeroesState>(HeroesState.Empty)
    private val _teamState = MutableStateFlow<TeamsState>(TeamsState.Empty)

    val heroState: StateFlow<HeroesState>
        get() = _heroState

    val teamState: StateFlow<TeamsState>
        get() = _teamState
}
