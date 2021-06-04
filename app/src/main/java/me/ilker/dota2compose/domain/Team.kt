package me.ilker.dota2compose.domain

data class Team(
    val lastMatchTime: Int,
    val losses: Int,
    val name: String,
    val rating: Float,
    val tag: String,
    val teamId: Int,
    val wins: Int
)
