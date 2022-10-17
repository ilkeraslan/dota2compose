package me.ilker.dota2compose.repository

import me.ilker.dota2compose.model.network.response.PlayerResponse
import me.ilker.dota2compose.service.PlayersService
import javax.inject.Inject

class PlayersRepositoryImpl @Inject constructor(private val playersService: PlayersService) : PlayersRepository {

    override suspend fun getPlayerByAccountId(accountId: Int): PlayerResponse? =
        playersService.getPlayerByAccountId(accountId)
}