package me.ilker.dota2compose.model.network.response

import kotlinx.serialization.SerialName

data class LeagueTeamResponse(
    @SerialName("team_id") val teamId: Long
)
