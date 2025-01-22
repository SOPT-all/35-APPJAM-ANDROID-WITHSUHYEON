package com.sopt.withsuhyeon.core.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.sopt.withsuhyeon.feature.findsuhyeon.component.MultiSelectRequirements
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequirementsBottomSheet(
    isVisible: Boolean,
    requirementsList: List<String>,
    selectedRequirementsList: List<String>,
    onConfirmClick: (List<String>) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    var tempSelectedList by remember { mutableStateOf(selectedRequirementsList) }
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
                    modifier = Modifier.padding(
                        horizontal = 16.dp
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.bottom_sheet_title_main_title),
                            style = typography.title02_B,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Text(
                            text = stringResource(R.string.bottom_sheet_title_sub_title),
                            style = typography.body03_B.merge(colors.Grey400),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp)
                    ) {
                        MultiSelectRequirements(
                            requirementsList = requirementsList,
                            selectList = tempSelectedList,
                            onRequirementsChipClick = { requirement ->
                                tempSelectedList = if (tempSelectedList.contains(requirement)) {
                                    tempSelectedList - requirement
                                } else {
                                    tempSelectedList + requirement
                                }
                            }
                        )
                    }
                    LargeButton(
                        text = stringResource(R.string.bottom_sheet_btn_select_done),
                        isDisabled = tempSelectedList.isEmpty(),
                        onClick = {
                            onConfirmClick(tempSelectedList)
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
fun PreviewRequirementsBottomSheet() {
    var isBottomSheetVisible by remember { mutableStateOf(false) }
    val selectedRequirementsList by remember { mutableStateOf(listOf<String>()) }

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
                text = "요청사항 바텀 시트"
            )
        }
        if (isBottomSheetVisible) {
            RequirementsBottomSheet(
                isVisible = true,
                requirementsList = listOf(
                    "사진 촬영",
                    "영상 통화",
                    "전화 통화"
                ),
                onConfirmClick = {
                    isBottomSheetVisible = false
                },
                onDismiss = {
                    isBottomSheetVisible = false
                },
                selectedRequirementsList = selectedRequirementsList
            )
        }
    }
}