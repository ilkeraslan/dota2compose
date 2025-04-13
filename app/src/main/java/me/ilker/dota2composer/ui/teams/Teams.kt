package me.ilker.dota2composer.ui.teams

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import me.ilker.dota2composer.model.domain.Team
import me.ilker.dota2composer.presenter.TeamsState

@ExperimentalUnitApi
@Composable
internal fun Teams(
    teamsState: TeamsState
) {
    when (teamsState) {
        TeamsState.Empty,
        is TeamsState.Error -> Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Something went wrong. Please try again.",
                color = MaterialTheme.colorScheme.primary
            )
        }

        is TeamsState.Loaded -> LazyColumn(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(teamsState.teams) {
                TeamDetails(team = it)
            }
        }

        TeamsState.Loading -> Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}

@Composable
private fun TeamDetails(
    team: Team
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = team.name,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Wins: ${team.wins}",
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Losses: ${team.losses}",
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@ExperimentalUnitApi
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun TeamsLoadedPreview() {
    Teams(
        teamsState = TeamsState.Loaded(
            teams = listOf(
                Team(
                    lastMatchTime = 100,
                    losses = 5,
                    name = "Team 1",
                    rating = 4.5f,
                    tag = "",
                    teamId = 1,
                    wins = 20
                ),
                Team(
                    lastMatchTime = 100,
                    losses = 5,
                    name = "Team 2",
                    rating = 4.5f,
                    tag = "",
                    teamId = 2,
                    wins = 20
                ),
                Team(
                    lastMatchTime = 100,
                    losses = 5,
                    name = "Team 3",
                    rating = 4.5f,
                    tag = "",
                    teamId = 3,
                    wins = 20
                )
            )
        )
    )
}

@ExperimentalUnitApi
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun TeamsPreview() {
    Teams(
        teamsState = TeamsState.Empty
    )
}
