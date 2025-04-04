package me.ilker.dota2composer.ui.screens

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import me.ilker.dota2composer.MainViewModel
import me.ilker.dota2composer.NavFactory
import me.ilker.dota2composer.Screen

class HeroesScreenFactory : NavFactory {
    @ExperimentalCoilApi
    @ExperimentalMaterialApi
    override fun create(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable(Screen.HeroesScreen.route) {
            val mainViewModel: MainViewModel = hiltViewModel()
            val heroState by mainViewModel.heroesState.collectAsState()

            HeroesScreen(heroState) {
                mainViewModel.getHeroes()
            }
        }
    }
}
