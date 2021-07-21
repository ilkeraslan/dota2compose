package me.ilker.dota2compose.ui.screens

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import me.ilker.dota2compose.MainViewModel
import me.ilker.dota2compose.NavFactory
import me.ilker.dota2compose.Screen

class TeamsScreenFactory : NavFactory {
    @ExperimentalMaterialApi
    override fun create(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable(Screen.TeamsScreen.route) {
            val mainViewModel: MainViewModel = hiltViewModel()
            val teamsState by mainViewModel.teamState.collectAsState()

            TeamsScreen(teamsState) {
//                navController.navigate(Screen.TeamsScreen.route + "/${it.destination.id}")
                mainViewModel.getTeams()
            }
        }
    }
}
