package me.ilker.dota2composer.repository

import me.ilker.dota2composer.model.network.response.PlayerByRankResponse

interface PlayersRepository {
    suspend fun getPlayersByRank(): List<PlayerByRankResponse>
}
