package me.ilker.dota2compose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import me.ilker.dota2compose.repository.LeaguesRepository
import me.ilker.dota2compose.repository.LeaguesRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    
    @Binds
    @ViewModelScoped
    abstract fun bindsLeaguesRepository(repositoryImpl: LeaguesRepositoryImpl): LeaguesRepository
}
