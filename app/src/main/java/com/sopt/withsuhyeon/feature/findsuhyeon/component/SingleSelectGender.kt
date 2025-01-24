package com.sopt.withsuhyeon.feature.findsuhyeon.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.KeyStorage.FEMALE
import com.sopt.withsuhyeon.core.util.KeyStorage.MALE
import com.sopt.withsuhyeon.feature.findsuhyeon.component.chip.SmallSelectChip

@Composable
fun SingleSelectGender(
    modifier: Modifier = Modifier,
    selectedGender: String,
    onSelect: (String?) -> Unit = {}
) {
    var selectElement by remember { mutableStateOf<String?>(selectedGender) }
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        SmallSelectChip(
            text = MALE,
            isSelected = selectElement == MALE,
            image =if (selectElement == MALE)
                ImageVector.vectorResource(R.drawable.img_boy_selected)
            else
                ImageVector.vectorResource(R.drawable.img_boy_default),
            onClick = {
                selectElement = MALE
                onSelect(MALE)
            },
            modifier = Modifier.weight(1f),
        )

        SmallSelectChip(
            text = FEMALE,
            isSelected = selectElement == FEMALE,
            image = if (selectElement == FEMALE)
                ImageVector.vectorResource(R.drawable.img_girl_selected)
            else
                ImageVector.vectorResource(R.drawable.img_girl_default),
            onClick = {
                selectElement = FEMALE
                onSelect(FEMALE)
            },
            modifier = Modifier.weight(1f)
        )
    }
}


@Composable
@Preview
fun PreviewSingleSelectGender() {
    SingleSelectGender(
        selectedGender = FEMALE
    )
}