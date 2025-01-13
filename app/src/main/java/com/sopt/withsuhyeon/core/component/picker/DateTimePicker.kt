package com.sopt.withsuhyeon.core.component.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kez.picker.Picker
import com.kez.picker.PickerState
import com.sopt.withsuhyeon.core.util.size.calculateWidth
import com.sopt.withsuhyeon.core.util.time.HOUR12_RANGE
import com.sopt.withsuhyeon.core.util.time.MINUTE_RANGE
import com.sopt.withsuhyeon.core.util.time.currentDate
import com.sopt.withsuhyeon.core.util.time.currentDateTime
import com.sopt.withsuhyeon.core.util.time.toKoreanDay
import com.sopt.withsuhyeon.ui.theme.Black
import com.sopt.withsuhyeon.ui.theme.Grey400
import com.sopt.withsuhyeon.ui.theme.Purple50
import com.sopt.withsuhyeon.ui.theme.White
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.plus

@Composable
fun DateTimePicker(
    modifier: Modifier = Modifier,
    startDateTime: LocalDateTime = currentDateTime,
    datePickerState: PickerState<String> = remember {
        PickerState(
            "${startDateTime.monthNumber}월 ${startDateTime.dayOfMonth}일 ${startDateTime.dayOfWeek.name.toKoreanDay()}"
        )
    },
    amPmPickerState: PickerState<String> = remember {
        val isAm = (startDateTime.hour < 12)
        PickerState(if (isAm) "오전" else "오후")
    },
    hourPickerState: PickerState<Int> = remember {
        val hourIn12HourFormat = if (startDateTime.hour == 0) 12 else startDateTime.hour % 12
        PickerState(hourIn12HourFormat)
    },
    minutePickerState: PickerState<String> = remember {
        val minuteStr = startDateTime.minute.toString().padStart(2, '0')
        PickerState(minuteStr)
    },
    dateItems: List<String> = remember {
        val endDate = LocalDate(2025, 12, 31)
        if (currentDate > endDate) emptyList()
        else {
            val totalDays = endDate.toEpochDays() - currentDate.toEpochDays() + 1
            (0 until totalDays).map { offset ->
                val date = currentDate.plus(DatePeriod(days = offset))
                "${date.monthNumber}월 ${date.dayOfMonth}일 ${date.dayOfWeek.name.toKoreanDay()}"
            }
        }
    },
    amPmItems: List<String> = listOf("오전", "오후"),
    hourItems: List<Int> = HOUR12_RANGE,
    minuteItems: List<String> = MINUTE_RANGE.map { it.toString().padStart(2, '0') },
    visibleItemsCount: Int = 3,
    itemPadding: PaddingValues = PaddingValues(top = 12.dp, bottom = 16.dp, start = 6.dp, end = 6.dp),
    textStyle: TextStyle = WithSuhyeonTheme.typography.title02_SB.copy(color = Grey400),
    selectedTextStyle: TextStyle = WithSuhyeonTheme.typography.title02_SB.copy(color = Black),
    fadingEdgeGradient: Brush = Brush.verticalGradient(
        0f to Color.Transparent,
        0.5f to Color.Black,
        1f to Color.Transparent
    ),
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically
) {
    val datePickerWidth = calculateWidth(dateItems, textStyle)
    val amPmPickerWidth = calculateWidth(amPmItems, textStyle)
    val hourPickerWidth = calculateWidth(hourItems.map { it.toString() }, textStyle)
    val minutePickerWidth = calculateWidth(minuteItems, textStyle)

    val density = LocalDensity.current
    val itemHeight = with(density) {
        selectedTextStyle.fontSize.toDp() +
                itemPadding.calculateTopPadding() +
                itemPadding.calculateBottomPadding()
    }

    Row(
        modifier = modifier.background(White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .background(color = White, shape = RoundedCornerShape(12.dp))
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(itemHeight)
                    .width(datePickerWidth)
                    .background(color = Purple50, shape = RoundedCornerShape(12.dp))
            )
            Picker(
                state = datePickerState,
                items = dateItems,
                startIndex = dateItems.indexOf(datePickerState.selectedItem),
                visibleItemsCount = visibleItemsCount,
                modifier = Modifier.width(datePickerWidth),
                textStyle = textStyle,
                selectedTextStyle = selectedTextStyle,
                textModifier = modifier.padding(1.dp),
                dividerColor = Color.Transparent,
                itemPadding = itemPadding,
                fadingEdgeGradient = fadingEdgeGradient,
                horizontalAlignment = horizontalAlignment,
                itemTextAlignment = verticalAlignment,
                isInfinity = true
            )
        }

        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .background(color = White, shape = RoundedCornerShape(12.dp))
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(itemHeight)
                    .width(amPmPickerWidth)
                    .background(color = Purple50, shape = RoundedCornerShape(12.dp))
            )
            Picker(
                state = amPmPickerState,
                items = amPmItems,
                startIndex = amPmItems.indexOf(amPmPickerState.selectedItem),
                visibleItemsCount = visibleItemsCount,
                modifier = Modifier.width(amPmPickerWidth),
                textStyle = textStyle,
                selectedTextStyle = selectedTextStyle,
                textModifier = Modifier.padding(itemPadding),
                dividerColor = Color.Transparent,
                itemPadding = itemPadding,
                fadingEdgeGradient = fadingEdgeGradient,
                horizontalAlignment = horizontalAlignment,
                itemTextAlignment = verticalAlignment,
                isInfinity = false
            )
        }

        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .background(color = White, shape = RoundedCornerShape(12.dp))
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(itemHeight)
                    .width(hourPickerWidth + minutePickerWidth + 10.dp)
                    .background(color = Purple50, shape = RoundedCornerShape(12.dp))
            )

            Row(
                modifier = Modifier.align(Alignment.Center),
            ) {
                Picker(
                    state = hourPickerState,
                    items = hourItems,
                    startIndex = hourItems.indexOf(hourPickerState.selectedItem),
                    visibleItemsCount = visibleItemsCount,
                    modifier = Modifier.width(hourPickerWidth),
                    textStyle = textStyle,
                    selectedTextStyle = selectedTextStyle,
                    textModifier = Modifier.padding(itemPadding),
                    dividerColor = Color.Transparent,
                    itemPadding = itemPadding,
                    fadingEdgeGradient = fadingEdgeGradient,
                    horizontalAlignment = horizontalAlignment,
                    itemTextAlignment = verticalAlignment
                )

                Picker(
                    state = minutePickerState,
                    items = minuteItems,
                    startIndex = minuteItems.indexOf(minutePickerState.selectedItem),
                    visibleItemsCount = visibleItemsCount,
                    modifier = Modifier.width(minutePickerWidth),
                    textStyle = textStyle,
                    selectedTextStyle = selectedTextStyle,
                    textModifier = Modifier.padding(itemPadding),
                    dividerColor = Color.Transparent,
                    itemPadding = itemPadding,
                    fadingEdgeGradient = fadingEdgeGradient,
                    horizontalAlignment = horizontalAlignment,
                    itemTextAlignment = verticalAlignment
                )
            }
        }
    }
}