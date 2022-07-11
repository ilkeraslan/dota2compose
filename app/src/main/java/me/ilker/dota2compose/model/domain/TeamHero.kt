package me.ilker.dota2compose.model.domain

data class TeamHero(
    val heroID: Long,
    val name: String? = null,
    val gamesPlayed: Int,
    val wins: Int
)
