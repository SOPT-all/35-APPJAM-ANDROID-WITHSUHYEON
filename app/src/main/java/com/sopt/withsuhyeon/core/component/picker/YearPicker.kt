package com.sopt.withsuhyeon.core.component.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.kez.picker.Picker
import com.kez.picker.PickerState
import com.kez.picker.rememberPickerState
import com.sopt.withsuhyeon.core.util.time.YEAR_RANGE
import com.sopt.withsuhyeon.core.util.time.currentDate
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography
import kotlinx.datetime.LocalDate

@Composable
fun YearPicker(
    modifier: Modifier = Modifier,
    yearPickerState: PickerState<Int> = rememberPickerState(currentDate.year),
    startLocalDate: LocalDate = currentDate,
    yearItems: List<Int> = YEAR_RANGE.reversed(),
    visibleItemsCount: Int = 3,
    itemPadding: PaddingValues = PaddingValues(top = 12.dp, bottom = 16.dp),
    textStyle: TextStyle = typography.title02_SB.copy(color = colors.Grey400),
    selectedTextStyle: TextStyle = typography.title02_SB.copy(color = colors.Black),
    dividerColor: Color = Transparent,
    fadingEdgeGradient: Brush = Brush.verticalGradient(
        0f to Transparent,
        0.5f to colors.Black,
        1f to Transparent
    ),
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically
) {
    val isYearPickerScrolled = remember { mutableStateOf(false) }

    LaunchedEffect(yearPickerState) {
        var isFirstEmission = true
        snapshotFlow { yearPickerState.selectedItem }
            .collect {
                if (isFirstEmission) {
                    isFirstEmission = false
                } else {
                    isYearPickerScrolled.value = true
                }
            }
    }

    val density = LocalDensity.current
    val itemHeight = with(density) {
        selectedTextStyle.fontSize.toDp() +
                itemPadding.calculateTopPadding() +
                itemPadding.calculateBottomPadding()
    }
    val totalHeight = itemHeight * visibleItemsCount

    Box(
        modifier = modifier
            .background(colors.White)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(totalHeight)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .height(itemHeight)
                .background(
                    color = if (isYearPickerScrolled.value) colors.Purple50 else colors.White,
                    shape = RoundedCornerShape(12.dp)
                )
        )
        Picker(
            state = yearPickerState,
            items = yearItems,
            startIndex = yearItems.indexOf(startLocalDate.year),
            visibleItemsCount = visibleItemsCount,
            textModifier = Modifier.padding(itemPadding),
            textStyle = textStyle,
            selectedTextStyle = selectedTextStyle,
            dividerColor = dividerColor,
            itemPadding = itemPadding,
            fadingEdgeGradient = fadingEdgeGradient,
            horizontalAlignment = horizontalAlignment,
            itemTextAlignment = verticalAlignment,
            isInfinity = false,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
        )
    }
}