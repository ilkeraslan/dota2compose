package me.ilker.dota2composer.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SteamPlayerResponse(
    @SerialName("steamid") val steamId: Long,
    @SerialName("communityvisibilitystate") val communityVisibilityState: Int,
    @SerialName("profilestate") val profileState: Int,
    @SerialName("personaname") val personaName: String,
    @SerialName("profileurl") val profileUrl: String,
    @SerialName("avatar") val avatar: String,
    @SerialName("avatarmedium") val avatarMedium: String,
    @SerialName("avatarfull") val avatarFull: String,
    @SerialName("avatarhash") val avatarHash: String,
    @SerialName("lastlogoff") val lastLogoff: Long,
    @SerialName("personastate") val personaState: Int,
    @SerialName("primaryclanid") val primaryClanId: String,
    @SerialName("timecreated") val timeCreated: Long,
    @SerialName("personastateflags") val personaStateFlags: Int
)
