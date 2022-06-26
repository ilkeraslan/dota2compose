package me.ilker.dota2compose.presenter

import me.ilker.dota2compose.model.domain.Team

sealed class TeamState {
    object Empty : TeamState()
    object Loading : TeamState()
    data class Loaded(
        val team: Team,
        val infoVisible: Boolean = false
    ) : TeamState()

    data class Error(val error: Throwable) : TeamState()
}
