package com.sopt.withsuhyeon.feature.findsuhyeon.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.feature.findsuhyeon.component.chip.LargeSelectChip

@Composable
fun MultiSelectRequirements(
    modifier: Modifier = Modifier,
    requirementsList: List<String>,
    onRequirementsChipClick: (String) -> Unit,
    selectList: List<String>
) {
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(requirementsList) { requirements->
            LargeSelectChip(
                text = requirements,
                isSelected = selectList.contains(requirements),
                image =  painterResource(R.drawable.dummy_ellipse),
                onClick = { onRequirementsChipClick(requirements) }
            )
        }
    }
}

@Composable
@Preview
fun PreviewMultiSelectRequirements() {
    var selectedRequirements by remember { mutableStateOf(listOf<String>()) }
    MultiSelectRequirements(
        requirementsList = listOf(
            "사진 촬영",
            "영상 통화",
            "전화 통화"
        ),
        onRequirementsChipClick = { requirements ->
            selectedRequirements = if (selectedRequirements.contains(requirements)) {
                selectedRequirements - requirements
            } else {
                selectedRequirements + requirements
            } },
        selectList = selectedRequirements
    )
}