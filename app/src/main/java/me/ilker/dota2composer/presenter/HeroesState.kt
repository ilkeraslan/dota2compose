package me.ilker.dota2composer.presenter

import me.ilker.dota2composer.model.domain.Hero

sealed class HeroesState {
    object Empty : HeroesState()
    object Loading : HeroesState()
    data class Loaded(val heroes: List<Hero>) : HeroesState()
    data class Error(val error: Throwable) : HeroesState()
}
