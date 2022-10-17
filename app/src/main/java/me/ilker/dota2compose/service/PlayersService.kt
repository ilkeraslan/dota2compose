
package me.ilker.dota2compose.service

import me.ilker.dota2compose.model.network.response.PlayerResponse
import retrofit2.http.GET

interface PlayersService {

    @GET("players/{accountId}")
    suspend fun getPlayerByAccountId(accountId: Int): PlayerResponse?
}