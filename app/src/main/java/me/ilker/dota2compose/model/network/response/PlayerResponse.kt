package me.ilker.dota2compose.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.ilker.dota2compose.model.domain.MmrEstimate
import me.ilker.dota2compose.model.domain.Player
import me.ilker.dota2compose.model.domain.Profile

@Serializable
data class MmrEstimateResponse(
    @SerialName("estimate") val estimate: Int? = null
) {
    fun toDomain(): MmrEstimate = MmrEstimate(
        estimate = estimate
    )
}

@Serializable
data class ProfileResponse(
    @SerialName("account_id") val accountId: Int? = null,
    @SerialName("personaname") val personaName: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("plus") val plus: Boolean? = null,
    @SerialName("cheese") val cheese: Int? = null,
    @SerialName("steamid") val steamId: String? = null,
    @SerialName("avatar") val avatar: String? = null,
    @SerialName("avatarmedium") val avatarMedium: String? = null,
    @SerialName("avatarfull") val avatarFull: String? = null,
    @SerialName("profileurl") val profileUrl: String? = null,
    @SerialName("last_login") val lastLogin: String? = null,
    @SerialName("loccountrycode") val locCountryCode: String? = null,
    @SerialName("is_contributor") val isContributor: Boolean? = null,
    @SerialName("is_subscriber") val isSubscriber: Boolean? = null,
) {
    fun toDomain(): Profile = Profile(
        accountId = accountId,
        personaName = personaName,
        name = name,
        plus = plus,
        cheese = cheese,
        steamId = steamId,
        avatar = avatar,
        avatarMedium = avatarMedium,
        avatarFull = avatarFull,
        profileUrl = profileUrl,
        lastLogin = lastLogin,
        locCountryCode = locCountryCode,
        isContributor = isContributor,
        isSubscriber = isSubscriber,
    )
}

@Serializable
data class PlayerResponse(
    @SerialName("solo_competitive_rank") val soloCompetitiveRank: Int? = null,
    @SerialName("competitive_rank") val competitiveRank: Int? = null,
    @SerialName("rank_tier") val rankTier: Int? = null,
    @SerialName("leaderboard_rank") val leaderboardRank: Int? = null,
    @SerialName("mmr_estimate") val mmrEstimate: MmrEstimateResponse? = null,
    @SerialName("profile") val profile: ProfileResponse? = null
) {
    fun toDomain(): Player = Player(
        soloCompetitiveRank = soloCompetitiveRank,
        competitiveRank = competitiveRank,
        rankTier = rankTier,
        leaderboardRank = leaderboardRank,
        mmrEstimate = mmrEstimate?.toDomain(),
        profile = profile?.toDomain()
    )
}
