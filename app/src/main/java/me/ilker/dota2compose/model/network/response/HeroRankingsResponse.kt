package me.ilker.dota2compose.model.network.response

import kotlinx.serialization.Serializable

@Serializable
data class HeroRankingsResponse(
    val rankings: List<HeroRankingResponse>? = null
)
