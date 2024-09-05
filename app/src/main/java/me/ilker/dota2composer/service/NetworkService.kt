package me.ilker.dota2composer.service

import me.ilker.dota2composer.model.network.response.HeroRankingsResponse
import me.ilker.dota2composer.model.network.response.HeroResponse
import me.ilker.dota2composer.model.network.response.PlayerResponse
import me.ilker.dota2composer.model.network.response.SearchResponse
import me.ilker.dota2composer.model.network.response.TeamHeroResponse
import me.ilker.dota2composer.model.network.response.TeamPlayerResponse
import me.ilker.dota2composer.model.network.response.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @GET("heroStats")
    suspend fun getHeroes(): List<HeroResponse>

    @GET("teams")
    suspend fun getTeams(): List<TeamResponse>

    @GET("teams/{team_id}/players")
    suspend fun getTeamPlayers(
        @Path("team_id") teamID: String
    ): List<TeamPlayerResponse>

    @GET("teams/{team_id}/heroes")
    suspend fun getTeamHeroes(
        @Path("team_id") teamID: String
    ): List<TeamHeroResponse>
    
    @GET("rankings")
    suspend fun getHeroRankings(
        @Query("hero_id") heroID: Int
    ): HeroRankingsResponse

    @GET("players/{account_id}")
    suspend fun getPlayer(
        @Path("account_id") accountID: String
    ): PlayerResponse

    @GET("search")
    suspend fun search(
        @Query("q") query: String
    ): List<SearchResponse>
}
