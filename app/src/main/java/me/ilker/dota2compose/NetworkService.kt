package me.ilker.dota2compose

import me.ilker.dota2compose.model.network.response.HeroResponse
import me.ilker.dota2compose.model.network.response.TeamPlayerResponse
import me.ilker.dota2compose.model.network.response.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Path

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
    ): List<TeamPlayerResponse>
}
