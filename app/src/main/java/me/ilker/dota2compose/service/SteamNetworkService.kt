package me.ilker.dota2compose.service

import me.ilker.dota2compose.model.network.response.SteamReponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SteamNetworkService {
    @GET("ISteamUser/GetPlayerSummaries/v0002/")
    suspend fun getPlayerSummary(
        @Query("key") key: String,
        @Query("steamids") steamids: String,
    ): SteamReponse
}