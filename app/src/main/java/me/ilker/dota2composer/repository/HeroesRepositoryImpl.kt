package me.ilker.dota2composer.repository

import javax.inject.Inject
import me.ilker.dota2composer.service.NetworkService

class HeroesRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : HeroesRepository {
    override suspend fun getHeroRankings(heroID: Int) = networkService.getHeroRankings(heroID)
}
