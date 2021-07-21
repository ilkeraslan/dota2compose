package me.ilker.dota2compose.ui.screens

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import me.ilker.dota2compose.MainViewModel
import me.ilker.dota2compose.NavFactory
import me.ilker.dota2compose.Screen

class HeroesScreenFactory : NavFactory {
    @ExperimentalCoilApi
    @ExperimentalMaterialApi
    override fun create(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable(Screen.HeroesScreen.route) {
            val mainViewModel: MainViewModel = hiltViewModel()
            val heroState by mainViewModel.heroState.collectAsState()

            HeroesScreen(heroState) {
//                navController.navigate(Screen.HeroesScreen.route + "/${it.destination.id}")
                mainViewModel.getHeroes()
            }
        }
    }
}
