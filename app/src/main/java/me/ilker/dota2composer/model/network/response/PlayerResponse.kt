package me.ilker.dota2composer.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerResponse(
    @SerialName("solo_competitive_rank") val soloCompetitiveRank: Int ? = null,
    @SerialName("competitive_rank") val competitiveRank: Int ? = null
)
