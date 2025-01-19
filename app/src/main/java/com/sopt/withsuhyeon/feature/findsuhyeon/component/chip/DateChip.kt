package com.sopt.withsuhyeon.feature.findsuhyeon.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.core.type.DateChipType
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun DateChip(
    dateChipType: DateChipType,
    date: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    val dynamicString = if(dateChipType.requiresDynamicString)
        stringResource(
            dateChipType.titleResId,
            date
        )
    else
        stringResource(dateChipType.titleResId)

    val textColor = if(isSelected)
        dateChipType.selectedTextColor
    else
        dateChipType.unSelectedTextColor

    Box(
        modifier = modifier.noRippleClickable(onClick)
            .padding(10.dp)
    ) {
        Text(
            text = dynamicString,
            style = dateChipType.typography.merge(color = textColor)
        )
    }
}
@Composable
@Preview
fun PreviewDateChip() {
    val modifier = Modifier
    val selectedDate = remember {
        mutableStateOf<String?>(null)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
            .padding(start = 8.dp)
            .background(colors.White)
    ) {
        DateChip(
            dateChipType = DateChipType.ALL,
            isSelected = selectedDate.value == null,
            onClick = {
                selectedDate.value = null
            },
            date = "전체",
        )
        DateChip(
            dateChipType = DateChipType.DATE,
            date = "1/25 (토)",
            isSelected = selectedDate.value == "1/25 (토)",
            onClick = {
                selectedDate.value = "1/25 (토)"
            }
        )
    }
}