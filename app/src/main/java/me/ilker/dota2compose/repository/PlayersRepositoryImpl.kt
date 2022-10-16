package me.ilker.dota2compose.repository

import javax.inject.Inject
import me.ilker.dota2compose.model.network.response.PlayerByRankResponse
import me.ilker.dota2compose.service.PlayersService

class PlayersRepositoryImpl @Inject constructor(
    private val playersService: PlayersService
) : PlayersRepository {
    override suspend fun getPlayersByRank(): List<PlayerByRankResponse> =
        playersService.getPlayersByRank()
}
