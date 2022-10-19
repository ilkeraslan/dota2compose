package me.ilker.dota2compose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import me.ilker.dota2compose.repository.HeroesRepository
import me.ilker.dota2compose.repository.HeroesRepositoryImpl
import me.ilker.dota2compose.repository.LeaguesRepository
import me.ilker.dota2compose.repository.LeaguesRepositoryImpl
import me.ilker.dota2compose.repository.PlayersRepository
import me.ilker.dota2compose.repository.PlayersRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    
    @Binds
    @ViewModelScoped
    abstract fun bindsLeaguesRepository(repositoryImpl: LeaguesRepositoryImpl): LeaguesRepository
    
    @Binds
    @ViewModelScoped
    abstract fun bindsPlayersRepository(repositoryImpl: PlayersRepositoryImpl): PlayersRepository
    
    @Binds
    @ViewModelScoped
    abstract fun bindsHeroesRepository(repositoryImpl: HeroesRepositoryImpl): HeroesRepository
}
