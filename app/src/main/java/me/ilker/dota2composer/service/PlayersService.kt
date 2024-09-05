package me.ilker.dota2composer.service

import me.ilker.dota2composer.model.network.response.PlayerByRankResponse
import retrofit2.http.GET

interface PlayersService {
    
    @GET("playersByRank")
    suspend fun getPlayersByRank(): List<PlayerByRankResponse>
}
