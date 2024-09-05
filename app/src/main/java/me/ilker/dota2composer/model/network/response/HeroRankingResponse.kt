package me.ilker.dota2composer.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroRankingResponse(
    @SerialName("account_id") val accountID: Long,
    @SerialName("last_login") val lastLogin: String? = null,
    @SerialName("rank_tier") val rankTier: Int? = null,
    @SerialName("personaname") val personaName: String? = null,
    val name: String?  = null,
    val score: Double? = null,
    val avatar: String? = null
)
