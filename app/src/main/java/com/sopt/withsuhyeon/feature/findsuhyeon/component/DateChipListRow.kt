package com.sopt.withsuhyeon.feature.findsuhyeon.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.core.type.DateChipType
import com.sopt.withsuhyeon.feature.findsuhyeon.component.chip.DateChip
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun DateChipListRow(
    selectedDate: String,
    dateList: List<String>,
    modifier: Modifier = Modifier,
    onSelect: (String) -> Unit
) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
            .padding(start = 8.dp)
            .background(colors.White)
    ) {
        items(dateList) { date ->
            DateChip(
                dateChipType = DateChipType.DATE,
                isSelected = selectedDate == date,
                onClick = {
                    onSelect(selectedDate)
                },
                date = date
            )
        }
    }
}

@Composable
@Preview
fun PreviewDateChipListRow() {
    var selectedDate by remember { mutableStateOf("전체") }
    val dateList = listOf(
        "전체",
        "1/25 (토)",
        "1/26 (일)",
        "1/27 (월)",
        "1/28 (화)",
        "1/29 (수)",
        "1/30 (목)",
        "1/31 (금)",
    )
    Box(modifier = Modifier.fillMaxSize()) {
        DateChipListRow(
            selectedDate,
            dateList,
            onSelect = { date ->
                selectedDate = date
            }
        )
    }
}