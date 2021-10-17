package me.ilker.dota2compose.ui.teams

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.ilker.dota2compose.domain.Team

@ExperimentalMaterialApi
@Composable
internal fun Team(
    modifier: Modifier = Modifier,
    team: Team,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp),
        backgroundColor = MaterialTheme.colors.background,
        onClick = { onClick() }
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

/*
 * Previews
 */
private val team = Team(
    lastMatchTime = 0,
    losses = 2,
    name = "Your Awesome Team",
    rating = 4.5f,
    tag = "Some tag",
    teamId = 2,
    wins = 10
)

@ExperimentalMaterialApi
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
fun TeamPreview() {
    Team(team = team) {}
}
