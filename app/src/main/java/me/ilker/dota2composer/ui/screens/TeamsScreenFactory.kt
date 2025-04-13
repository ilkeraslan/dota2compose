package me.ilker.dota2composer.ui.screens

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import me.ilker.dota2composer.MainViewModel
import me.ilker.dota2composer.NavFactory
import me.ilker.dota2composer.Screen

@ExperimentalUnitApi
class TeamsScreenFactory : NavFactory {
    override fun create(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController
    ) {
        navGraphBuilder.composable(Screen.TeamsScreen.route) {
            val mainViewModel: MainViewModel = hiltViewModel()
            val teamsState by mainViewModel.teamsState.collectAsState()

            TeamsScreen(
                teamsState = teamsState
            ) {
                mainViewModel.getTeams()
            }
        }
    }
}
