package me.ilker.dota2composer.model.network.response

import kotlinx.serialization.Serializable

@Serializable
data class HeroRankingsResponse(
    val rankings: List<HeroRankingResponse>? = null
)
