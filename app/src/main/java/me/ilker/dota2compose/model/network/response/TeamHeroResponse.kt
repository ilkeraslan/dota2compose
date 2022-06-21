package me.ilker.dota2compose.model.network.response

import kotlinx.serialization.SerialName
import me.ilker.dota2compose.model.domain.TeamHero

@kotlinx.serialization.Serializable
data class TeamHeroResponse(
    @SerialName("hero_id") val heroID: Long,
    @SerialName("name") val name: String? = null,
    @SerialName("games_played") val gamesPlayed: Int,
    @SerialName("wins") val wins: Int
) {
    fun toDomain() = TeamHero(
        heroID = heroID,
        name = name,
        gamesPlayed = gamesPlayed,
        wins = wins
    )
}
