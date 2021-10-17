package me.ilker.dota2compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.ilker.dota2compose.ui.screens.HeroesScreenFactory
import me.ilker.dota2compose.ui.screens.TeamsScreenFactory
import me.ilker.dota2compose.ui.theme.Dota2ComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalCoilApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Dota2ComposeTheme {
                MainScreen()
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
private fun MainScreen() {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

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
                backgroundColor = Color.LightGray,
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.CheckCircle,
                            contentDescription = "Icon Button"
                        )
                    }
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                scrollState.animateScrollTo(0)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Face,
                            contentDescription = "Icon Button"
                        )
                    }
                }
            )
        },
        bottomBar = bottomBar
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.HeroesScreen.route,
            modifier = Modifier
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
        backgroundColor = Color.LightGray,
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
