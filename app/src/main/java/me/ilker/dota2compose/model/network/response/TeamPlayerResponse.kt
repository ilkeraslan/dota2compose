package me.ilker.dota2compose.model.network.response

import kotlinx.serialization.SerialName
import me.ilker.dota2compose.model.domain.TeamPlayer

@kotlinx.serialization.Serializable
data class TeamPlayerResponse(
    @SerialName("account_id") val accountID: Long,
    @SerialName("name") val name: String? = null,
    @SerialName("games_played") val gamesPlayed: Int,
    @SerialName("wins") val wins: Int,
    @SerialName("is_current_team_member") val isCurrentMember: Boolean? = null,
) {
    fun toDomain() = TeamPlayer(
        accountID = accountID,
        name = name,
        gamesPlayed = gamesPlayed,
        wins = wins,
        isCurrentMember = isCurrentMember ?: false
    )
}
