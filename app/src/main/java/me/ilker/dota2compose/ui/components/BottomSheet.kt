package me.ilker.dota2compose.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun BottomSheet(
    modifier: Modifier = Modifier,
    initialState: BottomSheetState = BottomSheetState(initialValue = BottomSheetValue.Collapsed)
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = initialState
    )

    BottomSheetScaffold(
        modifier = modifier,
        sheetContent = { BottomSheetContent() },
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp
    ) {

    }
}

@Composable
private fun BottomSheetContent(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(text = "Title")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "A long warning description to make sure that the user knows what is going on")
        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = modifier.fillMaxWidth()) {
            Button(
                modifier = modifier.fillMaxWidth(),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "OK")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                modifier = modifier.fillMaxWidth(),
                onClick = { /*TODO*/ }) {
                Text(text = "CANCEL")
            }
        }
    }
}

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
fun BottomSheetPreview() {
    BottomSheet(initialState = BottomSheetState(BottomSheetValue.Expanded))
}
