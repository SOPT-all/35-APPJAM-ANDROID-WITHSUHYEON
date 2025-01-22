package com.sopt.withsuhyeon.feature.findsuhyeon.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.feature.findsuhyeon.component.chip.LargeSelectChip

@Composable
fun SingleSelectAge(
    ageList: List<String>,
    modifier: Modifier = Modifier,
    selectedAge: String?,
    onSelect: (String?) -> Unit = {},
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(ageList.size) { ageIndex ->
            LargeSelectChip(
                text = ageList[ageIndex],
                isSelected = selectedAge == ageList[ageIndex],
                onClick = {
                    onSelect(ageList[ageIndex])
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewSingleSelectAge() {
    SingleSelectAge(
        ageList = listOf(
            "20 ~ 24",
            "25 ~ 29",
            "30 ~ 34",
            "35 ~ 39",
            "40대 이상"
        ),
        selectedAge = null,
    )
}