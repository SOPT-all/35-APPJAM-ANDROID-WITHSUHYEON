package com.sopt.withsuhyeon.core.component.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.kez.picker.Picker
import com.kez.picker.PickerState
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.size.calculateWidth
import com.sopt.withsuhyeon.core.util.time.HOUR12_RANGE
import com.sopt.withsuhyeon.core.util.time.currentDate
import com.sopt.withsuhyeon.core.util.time.currentDateTime
import com.sopt.withsuhyeon.core.util.time.toDate
import com.sopt.withsuhyeon.core.util.time.toKoreanDay
import com.sopt.withsuhyeon.core.util.time.toTime
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.plus

@Composable
fun DateTimePicker(
    modifier: Modifier = Modifier,
    startDateTime: LocalDateTime = currentDateTime,
    onDateTimeSelected: (LocalDateTime?) -> Unit = {},
    datePickerState: PickerState<String> = remember {
        PickerState(
            "${startDateTime.monthNumber}월 ${startDateTime.dayOfMonth}일 ${startDateTime.dayOfWeek.name.toKoreanDay()}"
        )
    },
    hourPickerState: PickerState<Int> = remember {
        val roundedUpHour = if ((startDateTime.minute + 4) / 5 * 5 >= 60) {
            (if (startDateTime.hour % 12 == 11) 12 else (startDateTime.hour + 1) % 12)
        } else {
            if (startDateTime.hour == 0) 12 else startDateTime.hour % 12
        }
        PickerState(roundedUpHour)
    },
    minutePickerState: PickerState<String> = remember {
        val minute = ((startDateTime.minute + 4) / 5) * 5
        PickerState(if (minute == 60) "00" else minute.toString().padStart(2, '0'))
    },
    amPmPickerState: PickerState<String> = remember {
        PickerState(if (startDateTime.hour in 0..11) "오전" else "오후")
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
    amPmItems: List<String> = listOf(stringResource(R.string.am), stringResource(R.string.pm)),
    hourItems: List<Int> = HOUR12_RANGE,
    minuteItems: List<String> = (0..55 step 5).map { it.toString().padStart(2, '0') },
    visibleItemsCount: Int = 3,
    itemPadding: PaddingValues = PaddingValues(top = 11.dp, bottom = 15.dp, start = 5.dp, end = 5.dp),
    textStyle: TextStyle = typography.title02_SB.copy(color = colors.Grey400),
    selectedTextStyle: TextStyle = typography.title02_SB.copy(color = colors.Black),
    fadingEdgeGradient: Brush = Brush.verticalGradient(
        0f to Transparent,
        0.5f to colors.Black,
        1f to Transparent
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
    LaunchedEffect(datePickerState.selectedItem, amPmPickerState.selectedItem, hourPickerState.selectedItem, minutePickerState.selectedItem) {
        try {
            val selectedDate = datePickerState.selectedItem.toDate()
            val selectedTime = LocalTime(
                hourPickerState.selectedItem.toTime(amPmPickerState.selectedItem),
                minutePickerState.selectedItem.toInt()
            )
            val selectedDateTime = LocalDateTime(selectedDate, selectedTime)
            onDateTimeSelected(selectedDateTime)
        } catch (e: Exception) {
            println("Error parsing date: ${e.message}")
            onDateTimeSelected(null)
        }
    }
    Row(
        modifier = modifier.background(colors.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .background(color = colors.White, shape = RoundedCornerShape(12.dp))
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(itemHeight)
                    .width(datePickerWidth)
                    .background(color = colors.Purple50, shape = RoundedCornerShape(12.dp))
            )
            Picker(
                state = datePickerState,
                items = dateItems,
                startIndex = dateItems.indexOf(datePickerState.selectedItem),
                visibleItemsCount = visibleItemsCount,
                modifier = Modifier.width(datePickerWidth),
                textStyle = textStyle,
                selectedTextStyle = selectedTextStyle,
                dividerColor = Transparent,
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
                .background(color = colors.White, shape = RoundedCornerShape(12.dp))
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(itemHeight)
                    .width(amPmPickerWidth)
                    .background(color = colors.Purple50, shape = RoundedCornerShape(12.dp))
            )
            Picker(
                state = amPmPickerState,
                items = amPmItems,
                startIndex = amPmItems.indexOf(amPmPickerState.selectedItem),
                visibleItemsCount = visibleItemsCount,
                modifier = Modifier.width(amPmPickerWidth),
                textStyle = textStyle,
                selectedTextStyle = selectedTextStyle,
                dividerColor = Transparent,
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
                .background(color = colors.White, shape = RoundedCornerShape(12.dp))
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(itemHeight)
                    .width(hourPickerWidth + minutePickerWidth + 10.dp)
                    .background(color = colors.Purple50, shape = RoundedCornerShape(12.dp))
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
                    dividerColor = Transparent,
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
                    dividerColor = Transparent,
                    itemPadding = itemPadding,
                    fadingEdgeGradient = fadingEdgeGradient,
                    horizontalAlignment = horizontalAlignment,
                    itemTextAlignment = verticalAlignment
                )
            }
        }
    }
}