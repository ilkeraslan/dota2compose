package me.ilker.dota2compose.repository

import javax.inject.Inject
import me.ilker.dota2compose.model.network.response.LeagueMatchResponse
import me.ilker.dota2compose.model.network.response.LeagueResponse
import me.ilker.dota2compose.model.network.response.LeagueTeamResponse

// TODO - Inject LeaguesService as constructor param once implemented
class LeaguesRepositoryImpl @Inject constructor() : LeaguesRepository {
    override suspend fun getLeagues(): List<LeagueResponse>? {
        // TODO - Call LeaguesService client method here
        return null
    }
    
    override suspend fun getLeague(id: Long): LeagueResponse? {
        // TODO - Call LeaguesService client method here
        return null
    }
    
    override suspend fun getLeagueMatches(id: Long): List<LeagueMatchResponse>? {
        // TODO - Call LeaguesService client method here
        return null
    }
    
    override suspend fun getLeagueTeams(id: Long): List<LeagueTeamResponse>? {
        // TODO - Call LeaguesService client method here
        return null
    }
}
