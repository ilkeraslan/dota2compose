package me.ilker.dota2compose.ui.teams

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.ilker.dota2compose.domain.Team
import me.ilker.dota2compose.presenter.TeamsState
import me.ilker.dota2compose.ui.components.BottomSheet

@ExperimentalMaterialApi
@Composable
internal fun Teams(
    scope: CoroutineScope,
    scaffoldState: BottomSheetScaffoldState,
    teamsState: TeamsState.Loaded
) {
    BottomSheet(
        modifier = Modifier,
        scope = scope,
        scaffoldState = scaffoldState,
        onConfirm = {
            scope.launch {
                scaffoldState.bottomSheetState.collapse()
            }
        },
        onCancel = {
            scope.launch {
                scaffoldState.bottomSheetState.collapse()
            }
        },
        content = {
            BottomSheetContent(
                teamsState = teamsState,
                scope = scope,
                scaffoldState = scaffoldState
            )
        }
    )
}

@ExperimentalMaterialApi
@Composable
private fun BottomSheetContent(
    modifier: Modifier = Modifier,
    teamsState: TeamsState.Loaded,
    scope: CoroutineScope,
    scaffoldState: BottomSheetScaffoldState
) {
    LazyColumn(
        modifier = modifier.padding(bottom = 60.dp)
    ) {
        items(teamsState.teams) { team ->
            Team(team = team) {
                scope.launch {
                    scaffoldState.bottomSheetState.expand()
                }
            }
        }
    }
}

/*
 * Previews
 */
private val teams = listOf(
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
        scope = rememberCoroutineScope(),
        scaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.Collapsed)
        ),
        teamsState = TeamsState.Loaded(teams = teams)
    )
}
