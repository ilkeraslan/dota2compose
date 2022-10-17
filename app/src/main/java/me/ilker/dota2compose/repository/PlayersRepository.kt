package me.ilker.dota2compose.repository

import me.ilker.dota2compose.model.network.response.PlayerResponse

interface PlayersRepository {

    /**
     * accountId is the Steam32 ID of the player
     */
    suspend fun getPlayerByAccountId(accountId: Int): PlayerResponse?
}