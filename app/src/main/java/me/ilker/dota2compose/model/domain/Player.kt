package me.ilker.dota2compose.model.domain

data class MmrEstimate(
    val estimate: Int? = null
)

data class Profile(
    val accountId: Int? = null,
    val personaName: String? = null,
    val name: String? = null,
    val plus: Boolean? = null,
    val cheese: Int? = null,
    val steamId: String? = null,
    val avatar: String? = null,
    val avatarMedium: String? = null,
    val avatarFull: String? = null,
    val profileUrl: String? = null,
    val lastLogin: String? = null,
    val locCountryCode: String? = null,
    val isContributor: Boolean? = null,
    val isSubscriber: Boolean? = null,
)

data class Player(
    val soloCompetitiveRank: Int? = null,
    val competitiveRank: Int? = null,
    val rankTier: Int? = null,
    val leaderboardRank: Int? = null,
    val mmrEstimate: MmrEstimate? = null,
    val profile: Profile? = null
)
