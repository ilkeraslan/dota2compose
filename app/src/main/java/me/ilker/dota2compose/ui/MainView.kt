package me.ilker.dota2compose.ui

import androidx.compose.foundation.ScrollState
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
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.ilker.dota2compose.ui.theme.Teal200

@Composable
fun MainView(coroutineScope: CoroutineScope, scrollState: ScrollState, bottomBar: @Composable () -> Unit) {
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
                            coroutineScope.launch {
                                scrollState.animateScrollTo(0)
                            }
                        }) {
                        Icon(
                            imageVector = Icons.Outlined.Face,
                            contentDescription = "Icon Button"
                        )
                    }
                }
            )
        },
        bottomBar = bottomBar
    ) {}
}
