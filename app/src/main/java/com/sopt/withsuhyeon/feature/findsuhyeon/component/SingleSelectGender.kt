package com.sopt.withsuhyeon.feature.findsuhyeon.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.KeyStorage.FEMALE
import com.sopt.withsuhyeon.core.util.KeyStorage.MALE
import com.sopt.withsuhyeon.feature.findsuhyeon.component.chip.SmallSelectChip

@Composable
fun SingleSelectGender(
    selectElement: MutableState<String?>,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        SmallSelectChip(
            text = MALE,
            isSelected = selectElement.value == MALE,
            image = painterResource(R.drawable.dummy_ellipse),
            onClick = {
                selectElement.value = MALE
            },
            modifier = Modifier.weight(1f)
        )

        SmallSelectChip(
            text = FEMALE,
            isSelected = selectElement.value == FEMALE,
            image = painterResource(R.drawable.dummy_ellipse),
            onClick = {
                selectElement.value = FEMALE
            },
            modifier = Modifier.weight(1f)
        )
    }
}


@Composable
@Preview
fun PreviewSingleSelectGender() {
    val selectedGender = remember { mutableStateOf<String?>(null) }
    SingleSelectGender(
        selectElement = selectedGender
    )
}