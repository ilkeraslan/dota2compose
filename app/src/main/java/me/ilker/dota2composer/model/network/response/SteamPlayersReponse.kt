package me.ilker.dota2composer.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SteamReponse(
    @SerialName("response") val response: SteamPlayersReponse,
)

@Serializable
data class SteamPlayersReponse(
    @SerialName("players") val players: List<SteamPlayerResponse>,
)

