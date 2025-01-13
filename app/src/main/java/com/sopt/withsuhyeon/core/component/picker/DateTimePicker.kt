package com.sopt.withsuhyeon.core.component.picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kez.picker.Picker
import com.kez.picker.PickerState
import com.sopt.withsuhyeon.core.util.time.currentDateTime
import com.sopt.withsuhyeon.ui.theme.Black
import com.sopt.withsuhyeon.ui.theme.Grey400
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
            "${startDateTime.monthNumber}월 ${startDateTime.dayOfMonth}일 ${
                startDateTime.dayOfWeek.name.substring(0, 1)
            }"
        )
    },
    hourPickerState: PickerState<Int> = remember { PickerState(startDateTime.hour) },
    minutePickerState: PickerState<String> = remember {
        val minuteStr = startDateTime.minute.toString().padStart(2, '0')
        PickerState(minuteStr)
    },
    dateItems: List<String> = remember {
        val startDate = LocalDate(2025, 1, 1)
        (0 until 365).map { offset ->
            val date = startDate.plus(DatePeriod(days = offset))
            "${date.monthNumber}월 ${date.dayOfMonth}일 ${date.dayOfWeek.name.substring(0, 1)}"
        }
    },
    hourItems: List<Int> = (1..12).toList(),
    minuteItems: List<String> = (0..59).map { it.toString().padStart(2, '0') },
    visibleItemsCount: Int = 3,
    itemPadding: PaddingValues = PaddingValues(8.dp),
    textStyle: TextStyle = WithSuhyeonTheme.typography.title02_SB.copy(color = Grey400),
    selectedTextStyle: TextStyle = WithSuhyeonTheme.typography.title02_SB.copy(color = Black),
    fadingEdgeGradient: Brush = Brush.verticalGradient(
        0f to Color.Transparent,
        0.5f to Color.Black,
        1f to Color.Transparent
    ),
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    dividerThickness: Dp = 1.dp,
    dividerShape: Shape = RoundedCornerShape(10.dp),
    spacingBetweenPickers: Dp = 0.dp,
    pickerWidth: Dp = 120.dp
) {
    Surface(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Picker(
                state = datePickerState,
                items = dateItems,
                startIndex = dateItems.indexOf(datePickerState.selectedItem),
                visibleItemsCount = visibleItemsCount,
                modifier = Modifier.width(pickerWidth),
                textStyle = TextStyle(fontSize = 14.sp),
                selectedTextStyle = TextStyle(fontSize = 18.sp),
                textModifier = Modifier.padding(1.dp),
                dividerColor = Color.Transparent,
                itemPadding = itemPadding,
                fadingEdgeGradient = fadingEdgeGradient,
                horizontalAlignment = horizontalAlignment,
                itemTextAlignment = verticalAlignment,
                dividerThickness = dividerThickness,
                dividerShape = dividerShape
            )
            Spacer(modifier = Modifier.width(spacingBetweenPickers))

            Picker(
                state = hourPickerState,
                items = hourItems,
                startIndex = hourItems.indexOf(hourPickerState.selectedItem),
                visibleItemsCount = visibleItemsCount,
                modifier = Modifier.width(pickerWidth),
                textStyle = textStyle,
                selectedTextStyle = selectedTextStyle,
                textModifier = Modifier.padding(itemPadding),
                dividerColor = Color.Transparent,
                itemPadding = itemPadding,
                fadingEdgeGradient = fadingEdgeGradient,
                horizontalAlignment = horizontalAlignment,
                itemTextAlignment = verticalAlignment,
                dividerThickness = dividerThickness,
                dividerShape = dividerShape
            )
            Spacer(modifier = Modifier.width(spacingBetweenPickers))

            Picker(
                state = minutePickerState,
                items = minuteItems,
                startIndex = minuteItems.indexOf(minutePickerState.selectedItem),
                visibleItemsCount = visibleItemsCount,
                modifier = Modifier.width(pickerWidth),
                textStyle = textStyle,
                selectedTextStyle = selectedTextStyle,
                textModifier = Modifier.padding(itemPadding),
                dividerColor = Color.Transparent,
                itemPadding = itemPadding,
                fadingEdgeGradient = fadingEdgeGradient,
                horizontalAlignment = horizontalAlignment,
                itemTextAlignment = verticalAlignment,
                dividerThickness = dividerThickness,
                dividerShape = dividerShape
            )
        }
    }
}