package com.sopt.withsuhyeon.feature.findsuhyeon.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.feature.findsuhyeon.component.chip.LargeSelectChip

@Composable
fun SingleSelectAge(
    ageList: List<String>,
    modifier: Modifier = Modifier,
    onSelect: (String?) -> Unit = {}
) {
    var selectAge by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(ageList.size) { age ->
            LargeSelectChip(
                text = ageList[age],
                isSelected = selectAge == ageList[age],
                onClick = {
                    selectAge = ageList[age]
                    onSelect(ageList[age])
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
        )
    )
}