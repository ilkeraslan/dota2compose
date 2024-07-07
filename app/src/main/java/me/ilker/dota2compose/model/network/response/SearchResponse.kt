package me.ilker.dota2compose.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("account_id") val accountID: Int ? = null,
    @SerialName("personaname") val personaName: String ? = null
)
