package me.ilker.dota2compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint
import me.ilker.dota2compose.ui.screens.HeroesScreenFactory
import me.ilker.dota2compose.ui.screens.TeamsScreenFactory
import me.ilker.dota2compose.ui.theme.Dota2ComposeTheme

@ExperimentalUnitApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setContent {
            Dota2ComposeTheme {
                MainScreen()
            }
        }
    }
}

@ExperimentalUnitApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
private fun MainScreen() {
    val navController = rememberNavController()

    val bottomBar: @Composable () -> Unit = {
        AppBottomNavigation(
            navController = navController,
            items = bottomNavItems
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "dota2compose", color = Color.White) },
                backgroundColor = Color(0XFFA30900),
                actions = {}
            )
        },
        bottomBar = bottomBar
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.HeroesScreen.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            HeroesScreenFactory().create(
                navGraphBuilder = this,
                navController = navController
            )
            TeamsScreenFactory().create(
                navGraphBuilder = this,
                navController = navController
            )
        }
    }
}

@Composable
private fun AppBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavItem>
) {
    BottomNavigation(
        backgroundColor = Color(0XFFA30900),
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.map { it.screen }.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    screen.icon?.let {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.label
                        )
                    }
                },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}
