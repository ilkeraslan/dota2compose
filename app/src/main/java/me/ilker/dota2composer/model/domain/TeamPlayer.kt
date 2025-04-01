package me.ilker.dota2composer.model.domain

data class TeamPlayer(
    val accountID: Long? = null,
    val name: String? = null,
    val gamesPlayed: Int,
    val wins: Int,
    val isCurrentMember: Boolean
)
