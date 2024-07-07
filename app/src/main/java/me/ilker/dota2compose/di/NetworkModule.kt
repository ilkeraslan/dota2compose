package me.ilker.dota2compose.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import me.ilker.dota2compose.service.NetworkService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton
import me.ilker.dota2compose.service.PlayersService
import me.ilker.dota2compose.service.SteamNetworkService

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Provides
    @Singleton
    fun providesNetworkService(
        okHttpClient: OkHttpClient
    ): NetworkService = Retrofit.Builder()
        .baseUrl("https://api.opendota.com/api/")
        .client(okHttpClient)
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create(NetworkService::class.java)
    
    @Provides
    @Singleton
    fun providesPlayersService(
        okHttpClient: OkHttpClient
    ): PlayersService = Retrofit.Builder()
        .baseUrl("https://api.opendota.com/api/")
        .client(okHttpClient)
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create(PlayersService::class.java)

    @Provides
    @Singleton
    fun providesSteamService(
        okHttpClient: OkHttpClient
    ): SteamNetworkService = Retrofit.Builder()
        .baseUrl("https://api.steampowered.com/")
        .client(okHttpClient)
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create(SteamNetworkService::class.java)

    @Provides
    @Singleton
    fun providesOkHttp(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun providesLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
