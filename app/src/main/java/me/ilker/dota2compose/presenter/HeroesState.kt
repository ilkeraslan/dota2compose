package me.ilker.dota2compose.presenter

import me.ilker.dota2compose.model.domain.Hero

sealed class HeroesState {
    object Empty : HeroesState()
    object Loading : HeroesState()
    data class Loaded(val heroes: List<Hero>) : HeroesState()
    data class Error(val error: Throwable) : HeroesState()
}
