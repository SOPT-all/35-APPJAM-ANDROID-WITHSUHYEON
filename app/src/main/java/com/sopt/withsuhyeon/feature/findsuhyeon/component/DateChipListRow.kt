package com.sopt.withsuhyeon.feature.findsuhyeon.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.core.type.DateChipType
import com.sopt.withsuhyeon.core.util.time.toKoreanDay
import com.sopt.withsuhyeon.feature.findsuhyeon.component.chip.DateChip
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import java.time.LocalDate

@Composable
fun DateChipListRow(
    selectedDate: MutableState<Triple<String, String, String>?>,
    dateList: List<Triple<String, String, String>> = emptyList(),
    modifier: Modifier = Modifier
) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
            .padding(start = 8.dp)
            .background(colors.White)
    ) {
        item {
            DateChip(
                dateChipType = DateChipType.ALL,
                isSelected = selectedDate.value == null,
                onClick = {
                    selectedDate.value = null
                }
            )
        }
        items(dateList) { date ->
            DateChip(
                dateChipType = DateChipType.DATE,
                month = date.first,
                day = date.second,
                dayOfWeek = date.third,
                isSelected = selectedDate.value == date,
                onClick = {
                    selectedDate.value = date
                }
            )
        }
    }
}
private fun findSuhyeonDateList(): List<Triple<String, String, String>> {
    val today = LocalDate.now()
    val dateList = mutableListOf<Triple<String, String, String>>()

    for (i in 0..6) {
        val date = today.plusDays(i.toLong())
        dateList.add(
            Triple(
                date.monthValue.toString(),
                date.dayOfMonth.toString(),
                date.dayOfWeek.name.toKoreanDay()
            )
        )
    }

    return dateList
}

@Composable
@Preview
fun PreviewDateChipListRow() {
    val selectedDate = remember {
        mutableStateOf<Triple<String, String, String>?>(null)
    }
    val dateList = findSuhyeonDateList()
    DateChipListRow(
        selectedDate,
        dateList
    )
}