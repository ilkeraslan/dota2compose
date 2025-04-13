package me.ilker.dota2composer.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import me.ilker.dota2composer.presenter.TeamsState
import me.ilker.dota2composer.ui.teams.Teams

@Composable
fun TeamsScreen(
    teamsState: TeamsState = TeamsState.Empty,
    requestReload: () -> Unit
) {
    when (teamsState) {
        TeamsState.Empty -> requestReload()

        is TeamsState.Error -> Toast.makeText(
            LocalContext.current,
            teamsState.error.message,
            Toast.LENGTH_LONG
        ).show()

        is TeamsState.Loaded -> Teams(
            teamsState = teamsState
        )

        TeamsState.Loading -> Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}
