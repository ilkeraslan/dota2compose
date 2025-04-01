package me.ilker.dota2composer.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.ilker.dota2composer.model.domain.Team

@Serializable
data class TeamResponse(
    @SerialName("last_match_time") val lastMatchTime: Int,
    @SerialName("losses") val losses: Int,
    @SerialName("name") val name: String,
    @SerialName("rating") val rating: Float,
    @SerialName("tag") val tag: String,
    @SerialName("team_id") val teamId: Int,
    @SerialName("wins") val wins: Int
) {
    fun toDomain(): Team = Team(
        lastMatchTime = lastMatchTime,
        losses = losses,
        name = name,
        rating = rating,
        tag = tag,
        teamId = teamId,
        wins = wins
    )
}
