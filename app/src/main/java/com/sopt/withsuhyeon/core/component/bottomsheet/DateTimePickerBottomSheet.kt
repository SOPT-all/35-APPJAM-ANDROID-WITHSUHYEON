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
import com.sopt.withsuhyeon.core.component.picker.DateTimePicker
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.core.util.time.currentDateTime
import com.sopt.withsuhyeon.core.util.time.toFormattedString
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePickerBottomSheet(
    isVisible: Boolean,
    selectedDateString: String?,
    selectedDate: LocalDateTime?,
    onConfirmClick: (String?, LocalDateTime?) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    var tempSelectedDate by remember { mutableStateOf(selectedDate) }
    var tempSelectedDateString by remember { mutableStateOf(selectedDateString) }

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
                        style = typography.title02_B,
                        text = stringResource(R.string.bottom_sheet_title_date),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp)
                    ) {
                        DateTimePicker(
                            startDateTime = tempSelectedDate ?: currentDateTime,
                            onDateTimeSelected = { dateTime ->
                                tempSelectedDate = dateTime
                                if(tempSelectedDate != null)
                                    tempSelectedDateString = tempSelectedDate!!.toFormattedString()
                            }
                        )
                    }
                    LargeButton(
                        text = stringResource(R.string.bottom_sheet_btn_select_done),
                        isDisabled = tempSelectedDateString == null,
                        onClick = {
                            onConfirmClick(tempSelectedDateString, tempSelectedDate)
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
fun PreviewDateTimePickerBottomSheet() {
    var isBottomSheetVisible by remember { mutableStateOf(false) }
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
            DateTimePickerBottomSheet(
                isVisible = true,
                onConfirmClick = { i, e ->

                },
                onDismiss = {
                    isBottomSheetVisible = false
                },
                selectedDateString = "1월 25일 (토) 오후 2:00",
                selectedDate = LocalDateTime(LocalDate(2025, 1, 25), LocalTime(2,0,0))
            )
        }
    }
}