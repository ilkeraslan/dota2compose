package me.ilker.dota2compose.repository

import me.ilker.dota2compose.model.network.response.LeagueMatchResponse
import me.ilker.dota2compose.model.network.response.LeagueResponse
import me.ilker.dota2compose.model.network.response.LeagueTeamResponse

interface LeaguesRepository {
    suspend fun getLeagues(): List<LeagueResponse>?
    suspend fun getLeague(id: Long): LeagueResponse?
    suspend fun getLeagueMatches(id: Long): List<LeagueMatchResponse>?
    suspend fun getLeagueTeams(id: Long): List<LeagueTeamResponse>?
}
