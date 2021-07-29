package me.ilker.dota2compose.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.ilker.dota2compose.domain.Team
import me.ilker.dota2compose.presenter.TeamsState

@ExperimentalMaterialApi
@Composable
fun TeamsScreen(
    teamsState: TeamsState = TeamsState.Empty,
    requestReload: () -> Unit
) {
    when (teamsState) {
        TeamsState.Empty -> { requestReload() }
        is TeamsState.Error -> Toast.makeText(
            LocalContext.current,
            teamsState.error.message,
            Toast.LENGTH_LONG
        ).show()
        is TeamsState.Loaded -> LazyColumn(modifier = Modifier.padding(bottom = 60.dp)) {
            items(teamsState.teams) { team ->
                TeamCard(team = team)
            }
        }
        TeamsState.Loading -> Box { CircularProgressIndicator(Modifier.align(Alignment.Center)) }
    }
}

@ExperimentalMaterialApi
@Composable
fun TeamCard(team: Team, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp),
        backgroundColor = MaterialTheme.colors.background,
        onClick = { Log.i("CLICK", "OK") }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = team.name)
            Text(text = "Wins: ${team.wins}")
            Text(text = "Losses: ${team.losses}")
        }
    }
}

@ExperimentalMaterialApi
@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun TeamCardPreview() {
    TeamCard(
        team = Team(
            lastMatchTime = 2,
            losses = 100,
            name = "Evil Geniuses",
            rating = 3.2f,
            tag = "EG",
            teamId = 4,
            wins = 10
        ),
        modifier = Modifier
    )
}
