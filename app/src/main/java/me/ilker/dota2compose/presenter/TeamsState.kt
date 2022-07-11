package me.ilker.dota2compose.presenter

import me.ilker.dota2compose.model.domain.Team

sealed class TeamsState {
    object Empty : TeamsState()
    object Loading : TeamsState()
    data class Loaded(val teams: List<Team>) : TeamsState()
    data class Error(val error: Throwable) : TeamsState()
}
