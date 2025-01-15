package com.sopt.withsuhyeon.feature.findsuhyeon.component.drpodown.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.chip.MediumChip
import com.sopt.withsuhyeon.core.type.MediumChipType
import com.sopt.withsuhyeon.feature.findsuhyeon.component.drpodown.basic.BasicSelectDropDown
import com.sopt.withsuhyeon.feature.findsuhyeon.component.drpodown.text.TextDropDownItem
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun MediumChipDropDown(
    hint: String,
    isError: Boolean,
    modifier: Modifier = Modifier,
    mediumChipTypeList: List<MediumChipType> = emptyList(),
    errorMessage: String = "",
    onClick: () -> Unit,
) {
    BasicSelectDropDown(
        isError =  isError,
        errorMessage = errorMessage,
        modifier = modifier,
        onClick = onClick,
        mainContent = {
            if(mediumChipTypeList.isEmpty()) {
                TextDropDownItem(
                    modifier = Modifier.weight(1f),
                    value = null,
                    hint = hint
                )
            }
            else {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                ) {
                    mediumChipTypeList.forEach { chipType ->
                        MediumChip(
                            mediumChipType = chipType
                        )
                    }
                }
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_down),
                    contentDescription = stringResource(R.string.find_suhyeon_detail_meeting_information),
                    tint = colors.Grey400,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewMediumChipDropDown() {
    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val mediumChipTypeList = remember { mutableStateListOf<MediumChipType>() }
    MediumChipDropDown (
        hint = "눌러서 요청사항 선택하기",
        isError = isError,
        errorMessage = errorMessage,
        onClick = {
            mediumChipTypeList.add(
                MediumChipType.CATEGORY_PHOTO
            )
        },
        mediumChipTypeList = mediumChipTypeList
    )
}