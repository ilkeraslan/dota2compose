package me.ilker.dota2composer.repository

import me.ilker.dota2composer.model.network.response.HeroRankingsResponse

interface HeroesRepository {
    suspend fun getHeroRankings(heroID: Int): HeroRankingsResponse
}
