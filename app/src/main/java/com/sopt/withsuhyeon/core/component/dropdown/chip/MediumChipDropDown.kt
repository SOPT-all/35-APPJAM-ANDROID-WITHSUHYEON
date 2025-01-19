package com.sopt.withsuhyeon.core.component.dropdown.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.chip.MediumChip
import com.sopt.withsuhyeon.core.component.dropdown.basic.BasicSelectDropDown
import com.sopt.withsuhyeon.core.component.dropdown.text.TextDropDownItem
import com.sopt.withsuhyeon.core.type.MediumChipType
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun MediumChipDropDown(
    hint: String,
    isError: Boolean,
    modifier: Modifier = Modifier,
    errorMessage: String = "",
    onClick: () -> Unit,
    selectedList: List<String>
) {
    BasicSelectDropDown(
        isError =  isError,
        errorMessage = errorMessage,
        modifier = modifier,
        onClick = onClick,
        mainContent = {
            if(selectedList.isEmpty()) {
                TextDropDownItem(
                    modifier = Modifier.weight(1f),
                    value = null,
                    hint = hint
                )
            }
            else {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    selectedList.forEach { chipType ->
                        MediumChip(
                            mediumChipType = MediumChipType.CATEGORY,
                            dynamicString = chipType
                        )
                    }
                }
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_down),
                    contentDescription = stringResource(R.string.find_suhyeon_multi_select_chip),
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
    val selectedList by remember {
        mutableStateOf(
            listOf(
                "사진 촬영",
                "영상 통화"
            )
        )
    }
    MediumChipDropDown(
        hint = "눌러서 요청사항 선택하기",
        isError = false,
        onClick = {

        },
        selectedList = selectedList
    )
}