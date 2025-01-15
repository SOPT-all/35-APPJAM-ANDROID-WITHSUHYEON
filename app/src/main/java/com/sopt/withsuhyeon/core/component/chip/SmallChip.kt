package com.sopt.withsuhyeon.core.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.core.type.SmallChipType
import com.sopt.withsuhyeon.core.util.KeyStorage.TAKE_A_PHOTO
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun SmallChip(
    smallChipType: SmallChipType,
    modifier: Modifier = Modifier,
    dynamicString: String = "",
) {
    val contentString = if(smallChipType.requiresDynamicString)
        stringResource(smallChipType.titleResId, dynamicString)
    else
        stringResource(smallChipType.titleResId)
    Box(
        modifier = modifier.background(
            color = smallChipType.backgroundColor,
            shape = RoundedCornerShape(smallChipType.cornerRadius)
        )
            .padding(
                vertical = smallChipType.verticalPadding,
                horizontal = smallChipType.horizontalPadding
            )

    ) {
        Text(
            text = contentString,
            style = smallChipType.typography.merge(color = smallChipType.textColor)
        )
    }
}

@Composable
@Preview
fun PreviewSmallChip() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .background(colors.White)
    ) {
        SmallChip(
            dynamicString = TAKE_A_PHOTO,
            smallChipType = SmallChipType.CATEGORY
        )
        SmallChip(
            smallChipType = SmallChipType.MATCH
        )
        SmallChip(
            smallChipType = SmallChipType.FINISHED
        )
        SmallChip(
            dynamicString = "3",
            smallChipType = SmallChipType.IMMINENT_D_DAY
        )
        SmallChip(
            dynamicString = "10",
            smallChipType = SmallChipType.D_DAY
        )
    }
}