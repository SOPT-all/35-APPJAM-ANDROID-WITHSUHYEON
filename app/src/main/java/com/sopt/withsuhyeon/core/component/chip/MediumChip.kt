package com.sopt.withsuhyeon.core.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.core.type.MediumChipType
import com.sopt.withsuhyeon.core.util.KeyStorage.TAKE_A_PHOTO
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun MediumChip(
    mediumChipType: MediumChipType,
    modifier: Modifier = Modifier,
    dynamicString: String = "",
) {
    val contentString = if(mediumChipType.requiresDynamicString)
        stringResource(mediumChipType.titleResId, dynamicString)
    else
        stringResource(mediumChipType.titleResId)
    Box(
        modifier = modifier.background(
                color = mediumChipType.backgroundColor,
                shape = RoundedCornerShape(mediumChipType.cornerRadius)
            )
            .padding(
                vertical = mediumChipType.verticalPadding,
                horizontal = mediumChipType.horizontalPadding
            )

    ) {
        Text(
            text = contentString,
            style = mediumChipType.typography.merge(color = mediumChipType.textColor)
        )
    }
}

@Composable
@Preview
fun PreviewMediumChip() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
            .background(colors.White)
    ) {
        MediumChip(
            mediumChipType = MediumChipType.CATEGORY_PHOTO
        )
        MediumChip(
            mediumChipType = MediumChipType.MATCH
        )
        MediumChip(
            mediumChipType = MediumChipType.FINISHED
        )
        MediumChip(
            dynamicString = "+99",
            mediumChipType = MediumChipType.CHAT
        )
    }
}