package me.ilker.dota2compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.ilker.dota2compose.domain.Hero
import me.ilker.dota2compose.domain.Team
import me.ilker.dota2compose.network.NetworkService

sealed class HeroState {
    data class Success(val heroes: List<Hero>) : HeroState()
    data class Error(val error: Throwable) : HeroState()
}

sealed class TeamState {
    data class Success(val teams: List<Team>) : TeamState()
    data class Error(val error: Throwable) : TeamState()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: NetworkService
) : ViewModel() {

    fun getHeroes() {
        viewModelScope.launch {
            try {
                val heroes = apiService.getHeroes().map { it.toDomain() }
                _heroState.value = HeroState.Success(heroes)
            } catch (e: Exception) {
                _heroState.value = HeroState.Error(e)
            }
        }
    }

    fun getTeams() {
        viewModelScope.launch {
            try {
                val teams = apiService.getTeams().map { it.toDomain() }
                _teamState.value = TeamState.Success(teams)
            } catch (e: Exception) {
                _teamState.value = TeamState.Error(e)
            }
        }
    }

    private val _heroState = MutableStateFlow<HeroState>(HeroState.Success(mutableListOf()))
    private val _teamState = MutableStateFlow<TeamState>(TeamState.Success(mutableListOf()))

    val heroState: StateFlow<HeroState>
        get() = _heroState

    val teamState: StateFlow<TeamState>
        get() = _teamState
}
