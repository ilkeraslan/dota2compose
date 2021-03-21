package me.ilker.dota2compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.ilker.dota2compose.domain.Hero
import me.ilker.dota2compose.network.NetworkService

sealed class HeroState {
    data class Success(val heroes: List<Hero>) : HeroState()
    data class Error(val error: Throwable) : HeroState()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: NetworkService
) : ViewModel() {

    fun getHeroes() {
        viewModelScope.launch {
            try {
                val base = apiService.getHeroes().map { it.toDomain() }
                _state.value = HeroState.Success(base)
            } catch (e: Exception) {
                _state.value = HeroState.Error(e)
            }
        }
    }

    private val _state = MutableStateFlow<HeroState>(HeroState.Success(mutableListOf()))
    val state: StateFlow<HeroState>
        get() = _state
}