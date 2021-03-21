package me.ilker.dota2compose.network

import retrofit2.http.GET

interface NetworkService {

    @GET("heroStats")
    suspend fun getHeroes(): List<HeroResponse>
}