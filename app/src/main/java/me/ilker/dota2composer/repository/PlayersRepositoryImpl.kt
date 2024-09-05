package me.ilker.dota2composer.repository

import javax.inject.Inject
import me.ilker.dota2composer.model.network.response.PlayerByRankResponse
import me.ilker.dota2composer.service.PlayersService

class PlayersRepositoryImpl @Inject constructor(
    private val playersService: PlayersService
) : PlayersRepository {
    override suspend fun getPlayersByRank(): List<PlayerByRankResponse> =
        playersService.getPlayersByRank()
}
