package me.ilker.dota2compose.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
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
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable () -> Unit
) {
    BottomSheetScaffold(
        modifier = modifier,
        sheetContent = sheetContent,
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
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    BottomSheet(
        scaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.Expanded)
        ),
        sheetContent = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TextButton(
                    onClick = {
                        scope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    }
                ) {
                    Text(text = "OK")
                }

                TextButton(
                    onClick = {
                        scope.launch {
                            scaffoldState.bottomSheetState.collapse()
                        }
                    }
                ) {
                    Text(text = "CANCEL")
                }
            }
        },
        content = {
            Text(
                text = "Content Title",
                fontSize = 20.sp
            )
        }
    )
}
