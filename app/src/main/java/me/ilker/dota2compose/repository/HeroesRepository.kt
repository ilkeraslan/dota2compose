package me.ilker.dota2compose.repository

import me.ilker.dota2compose.model.network.response.HeroRankingsResponse

interface HeroesRepository {
    suspend fun getHeroRankings(heroID: Int): HeroRankingsResponse
}
