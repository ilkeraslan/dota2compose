package me.ilker.dota2compose.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun BottomSheet(
    modifier: Modifier = Modifier,
    scope: CoroutineScope = rememberCoroutineScope(),
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.Collapsed)
    ),
    content: @Composable () -> Unit
) {
    BottomSheetScaffold(
        modifier = modifier,
        sheetContent = { BottomSheetContent() },
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        BoxWithConstraints(modifier) {
            Box(Modifier.fillMaxSize()) {
                content()
                Scrim(
                    onDismiss = {
                        if (scaffoldState.bottomSheetState.targetValue == BottomSheetValue.Collapsed) {
                            scope.launch { scaffoldState.bottomSheetState.collapse() }
                        }
                    },
                    visible = scaffoldState.bottomSheetState.targetValue != BottomSheetValue.Collapsed
                )
            }
        }
    }
}

@Composable
private fun BottomSheetContent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Title")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "A long warning description to make sure that the user knows what is going on")
        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = modifier.fillMaxWidth()) {
            TextButton(
                modifier = modifier.fillMaxWidth(),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "OK")
            }
            Spacer(modifier = Modifier.height(10.dp))
            TextButton(
                modifier = modifier.fillMaxWidth(),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "CANCEL")
            }
            Spacer(modifier = Modifier.height(80.dp))
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
    BottomSheet(
        scaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.Expanded)
        ),
        content = {
            Text(
                text = "Content Title",
                fontSize = 20.sp
            )
        }
    )
}
