package me.ilker.dota2composer.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerByRankResponse(
    @SerialName("account_id") val accountID: Long? = null
)
