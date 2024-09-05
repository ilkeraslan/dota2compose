package me.ilker.dota2composer.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import me.ilker.dota2composer.model.domain.Team
import me.ilker.dota2composer.presenter.TeamState
import me.ilker.dota2composer.presenter.TeamsState
import me.ilker.dota2composer.ui.teams.Team
import me.ilker.dota2composer.ui.teams.Teams

@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun TeamsScreen(
    teamsState: TeamsState = TeamsState.Empty,
    teamState: TeamState = TeamState.Empty,
    onTeamSelected: (Team) -> Unit,
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
            teamsState = teamsState,
            teamState = teamState,
            onTeamSelected = onTeamSelected
        )

        TeamsState.Loading -> Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}

/**
 * Previews
 */
@ExperimentalUnitApi
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
    Team(
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
    ) { /*No-op*/ }
}
