package me.ilker.dota2compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.ilker.dota2compose.ui.screens.HeroesScreen
import me.ilker.dota2compose.ui.theme.Dota2ComposeTheme
import me.ilker.dota2compose.ui.theme.Teal200

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
        }
    ) {
        HeroesScreen(viewModel = mainViewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Dota2ComposeTheme {}
}