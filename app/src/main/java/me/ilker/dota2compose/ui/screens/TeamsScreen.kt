package me.ilker.dota2compose.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import me.ilker.dota2compose.model.domain.Team
import me.ilker.dota2compose.presenter.TeamsState
import me.ilker.dota2compose.ui.teams.Team
import me.ilker.dota2compose.ui.teams.Teams

@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun TeamsScreen(
    teamsState: TeamsState = TeamsState.Empty,
    requestReload: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    when (teamsState) {
        TeamsState.Empty -> requestReload()
        is TeamsState.Error -> Toast.makeText(
            LocalContext.current,
            teamsState.error.message,
            Toast.LENGTH_LONG
        ).show()
        is TeamsState.Loaded -> Teams(
            scope = scope,
            scaffoldState = scaffoldState,
            teamsState = teamsState
        )
        TeamsState.Loading -> Box { CircularProgressIndicator(Modifier.align(Alignment.Center)) }
    }
}

/*
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
