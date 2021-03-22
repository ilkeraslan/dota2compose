package me.ilker.dota2compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.ilker.dota2compose.ui.screens.HeroesScreen
import me.ilker.dota2compose.ui.screens.TeamsScreen
import me.ilker.dota2compose.ui.theme.Dota2ComposeTheme
import me.ilker.dota2compose.ui.theme.Teal200

sealed class Screens(val route: String, val label: String, val icon: ImageVector? = null) {
    object HeroesScreen : Screens("Heroes", "Heroes", Icons.Outlined.Person)
    object TeamsScreen : Screens("Teams", "Teams", Icons.Outlined.Warning)
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Dota2ComposeTheme {
                ComposeMainScreen(mainViewModel)
            }
        }
    }
}

@Composable
private fun ComposeMainScreen(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    val bottomNavigationItems = listOf(Screens.HeroesScreen, Screens.TeamsScreen)
    val bottomBar: @Composable () -> Unit = { Dota2ComposeBottomNavigation(navController, bottomNavigationItems) }

    NavHost(navController, startDestination = Screens.HeroesScreen.route) {
        composable(Screens.HeroesScreen.route) {
            HeroesScreen(mainViewModel) {
                navController.navigate(Screens.HeroesScreen.route + "/${it.destination.id}")
            }
        }
        composable(Screens.TeamsScreen.route) {
            TeamsScreen(mainViewModel) {
                navController.navigate(Screens.TeamsScreen.route+ "/${it.destination.id}")
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "dota2compose", color = Color.White) },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Menu,
                        contentDescription = "Icon Button",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },
                backgroundColor = Teal200,
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.CheckCircle,
                            contentDescription = "Icon Button"
                        )
                    }
                    IconButton(
                        onClick = {
/*                                        coroutineScope.launch {
                                            scrollState.animateScrollToItem(index = 0)
                                        }*/
                        }) {
                        Icon(
                            imageVector = Icons.Outlined.Face,
                            contentDescription = "Icon Button"
                        )
                    }
                }
            )
        }, bottomBar = bottomBar
    ){
        navController.currentDestination?.arguments?.entries?.first()?.value?.defaultValue.toString().toScreen(mainViewModel = mainViewModel)
    }
}

@Composable
private fun String.toScreen(mainViewModel: MainViewModel) {
    when (this) {
        "Heroes" -> HeroesScreen(viewModel = mainViewModel) {}
        "Teams" -> TeamsScreen(viewModel = mainViewModel) {}
    }
}

@Composable
private fun Dota2ComposeBottomNavigation(
    navController: NavHostController,
    items: List<Screens>
) {
    BottomNavigation(
        backgroundColor = Teal200,
        contentColor = Color.White
    ) {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { screen.icon?.let { Icon(screen.icon, contentDescription = screen.label) } },
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

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Dota2ComposeTheme {}
}