package me.ilker.dota2compose.repository

import me.ilker.dota2compose.model.network.response.PlayerByRankResponse

interface PlayersRepository {
    suspend fun getPlayersByRank(): List<PlayerByRankResponse>
}
