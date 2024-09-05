package me.ilker.dota2composer.model.network.response

import kotlinx.serialization.SerialName

data class LeagueTeamResponse(
    @SerialName("team_id") val teamId: Long
)
