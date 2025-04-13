package me.ilker.dota2composer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint
import me.ilker.dota2composer.ui.screens.HeroesScreenFactory
import me.ilker.dota2composer.ui.screens.TeamsScreenFactory
import me.ilker.dota2composer.ui.theme.Dota2ComposeTheme

@ExperimentalCoilApi
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

@ExperimentalCoilApi
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
    BottomAppBar(
        containerColor = Color(0XFFA30900),
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Row(modifier = Modifier.fillMaxWidth()) {
            items.map { it.screen }.forEach { screen ->
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clickable {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route)
                            }
                        },
                    text = screen.label,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
