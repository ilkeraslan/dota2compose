@file:Suppress("UNCHECKED_CAST")

package me.ilker.dota2compose.ui.teams

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.ilker.dota2compose.model.domain.Team
import me.ilker.dota2compose.presenter.TeamState
import me.ilker.dota2compose.presenter.TeamsState
import me.ilker.dota2compose.ui.components.BottomSheet

@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
internal fun Teams(
    teamsState: TeamsState.Loaded,
    teamState: TeamState,
    onTeamSelected: (Team) -> Unit
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    LaunchedEffect(teamState) {
        if (teamState is TeamState.Loading || teamState is TeamState.Loaded) {
            scaffoldState.bottomSheetState.expand()
        } else {
            scaffoldState.bottomSheetState.collapse()
        }
    }

    BottomSheet(
        modifier = Modifier,
        scope = scope,
        scaffoldState = scaffoldState,
        sheetContent = sheetContent(
            scope = scope,
            scaffoldState = scaffoldState,
            content = teamDetails(
                teamState = teamState
            )
        ),
        content = {
            BottomSheetContent(
                teamsState = teamsState,
                onSelect = onTeamSelected
            )
        }
    )
}

@Composable
fun teamDetails(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
    teamState: TeamState
): @Composable (() -> Unit) = {
    LazyColumn(modifier = modifier) {
        item {
            when (teamState) {
                TeamState.Empty -> { /* no-op */
                }

                is TeamState.Error -> Text(text = "Error")
                is TeamState.Loaded -> TeamDetails2(teamState)
                TeamState.Loading -> Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

@Composable
private fun TeamDetails2(
    teamState: TeamState.Loaded
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = teamState.team.name,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        teamState.team.players?.let { players ->
            players.forEach { player ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = player.name ?: "Unknown Player")
                        Text(text = player.wins.toString().plus(" wins"))
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        teamState.team.heroes?.let { heroes ->
            heroes.forEach { hero ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = hero.name ?: "Unknown Hero")
                        Text(text = hero.wins.toString().plus(" wins"))
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun sheetContent(
    scope: CoroutineScope,
    scaffoldState: BottomSheetScaffoldState,
    content: @Composable () -> Unit = {}
): @Composable (ColumnScope.() -> Unit) = {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        content()

        TextButton(
            modifier = Modifier.align(CenterHorizontally),
            onClick = {
                scope.launch {
                    scaffoldState.bottomSheetState.collapse()
                }
            }
        ) {
            Text(text = "OK")
        }

        TextButton(
            modifier = Modifier.align(CenterHorizontally),
            onClick = {
                scope.launch {
                    scaffoldState.bottomSheetState.collapse()
                }
            }
        ) {
            Text(text = "CANCEL")
        }
    }
}

@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
private fun <T> BottomSheetContent(
    modifier: Modifier = Modifier,
    teamsState: TeamsState.Loaded,
    onSelect: (T) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }

        items(teamsState.teams) { team ->
            Team(
                team = team,
                onClick = { onSelect(team as T) }
            )
        }
    }
}

/*
 * Previews
 */
private
val teams = listOf(
    Team(
        lastMatchTime = 0,
        losses = 10,
        name = "My Awesome Team",
        rating = 4.5f,
        tag = "Some tag",
        teamId = 1,
        wins = 0
    ),
    Team(
        lastMatchTime = 0,
        losses = 2,
        name = "Your Awesome Team",
        rating = 4.5f,
        tag = "Some tag",
        teamId = 2,
        wins = 10
    )
)

@ExperimentalUnitApi
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
fun TeamsPreview() {
    Teams(
        teamsState = TeamsState.Loaded(teams = teams),
        onTeamSelected = {},
        teamState = TeamState.Empty
    )
}
