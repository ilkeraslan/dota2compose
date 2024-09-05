package me.ilker.dota2composer.presenter

import me.ilker.dota2composer.model.domain.Team

sealed class TeamState {
    object Empty : TeamState()
    object Loading : TeamState()
    data class Loaded(val team: Team) : TeamState()
    data class Error(val error: Throwable) : TeamState()
}
