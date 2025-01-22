package com.sopt.withsuhyeon.core.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.bottomsheet.draghandle.BottomSheetDragHandle
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.feature.findsuhyeon.component.SingleSelectAge
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgeBottomSheet(
    isVisible: Boolean,
    ageList: List<String>,
    selectedAge: String?,
    onConfirmClick: (String?) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    var tempSelectedAge by remember { mutableStateOf(selectedAge) }

    if (isVisible) {
        Box(modifier = modifier.wrapContentHeight()) {
            ModalBottomSheet(
                onDismissRequest = { onDismiss() },
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                containerColor = colors.White,
                modifier = Modifier
                    .fillMaxWidth(),
                dragHandle = {
                    BottomSheetDragHandle()
                }
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.bottom_sheet_title_age),
                        style = typography.title02_B,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp)
                    ) {
                        SingleSelectAge(
                            ageList = ageList,
                            selectedAge = tempSelectedAge,
                            onSelect = { age ->
                                tempSelectedAge = age
                            }
                        )
                    }
                    LargeButton(
                        text = stringResource(R.string.bottom_sheet_btn_select_done),
                        isDisabled = tempSelectedAge.isNullOrEmpty(),
                        onClick = {
                            onConfirmClick(tempSelectedAge)
                            onDismiss()
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAgeBottomSheet() {
    var isBottomSheetVisible by remember { mutableStateOf(false) }
    var selectedAge by remember { mutableStateOf<String?>("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .noRippleClickable { isBottomSheetVisible = true }
                .padding(horizontal = 24.dp, vertical = 12.dp)
                .background(colors.Purple100),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "나이 바텀 시트"
            )
        }
        if (isBottomSheetVisible) {
            AgeBottomSheet(
                isVisible = true,
                ageList = listOf(
                    "20 ~ 24",
                    "25 ~ 29",
                    "30 ~ 34",
                    "35 ~ 39",
                    "40대 이상"
                ),
                onConfirmClick = { age ->
                    selectedAge = age
                    isBottomSheetVisible = false
                },
                onDismiss = {
                    isBottomSheetVisible = false
                },
                selectedAge = ""
            )
        }
    }
}