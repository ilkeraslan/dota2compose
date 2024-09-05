package me.ilker.dota2composer.model.network.response

import kotlinx.serialization.SerialName

data class LeagueMatchResponse(
    @SerialName("match_id") val matchId: Long
)
