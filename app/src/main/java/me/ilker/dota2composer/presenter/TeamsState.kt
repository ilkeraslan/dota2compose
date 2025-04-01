package me.ilker.dota2composer.presenter

import me.ilker.dota2composer.model.domain.Team

sealed class TeamsState {
    object Empty : TeamsState()
    object Loading : TeamsState()
    data class Loaded(val teams: List<Team>) : TeamsState()
    data class Error(val error: Throwable) : TeamsState()
}
