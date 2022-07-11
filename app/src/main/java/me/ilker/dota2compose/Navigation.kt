package me.ilker.dota2compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface NavFactory {
    fun create(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController
    )
}

sealed class Screen(
    val route: String,
    val label: String,
    val icon: ImageVector? = null
) {
    object HeroesScreen : Screen(
        route = "Heroes",
        label = "Heroes",
        icon = Icons.Outlined.Person
    )

    object TeamsScreen : Screen(
        route = "Teams",
        label = "Teams",
        icon = Icons.Outlined.Warning
    )
}

data class BottomNavItem(val screen: Screen)

val bottomNavItems = listOf(
    BottomNavItem(Screen.HeroesScreen),
    BottomNavItem(Screen.TeamsScreen)
)
