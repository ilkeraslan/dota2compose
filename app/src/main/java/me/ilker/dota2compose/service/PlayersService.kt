package me.ilker.dota2compose.service

import me.ilker.dota2compose.model.network.response.PlayerByRankResponse
import retrofit2.http.GET

interface PlayersService {
    
    @GET("playersByRank")
    suspend fun getPlayersByRank(): List<PlayerByRankResponse>
}
