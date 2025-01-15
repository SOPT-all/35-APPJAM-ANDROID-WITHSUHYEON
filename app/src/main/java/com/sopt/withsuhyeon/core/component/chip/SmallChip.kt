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
import com.sopt.withsuhyeon.core.type.SmallChipType
import com.sopt.withsuhyeon.core.util.KeyStorage.TAKE_A_PHOTO
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun SmallChip(
    type: SmallChipType,
    modifier: Modifier = Modifier,
    dynamicString: String = "",
) {
    val contentString = if(type.requiresDynamicString)
        stringResource(type.titleResId, dynamicString)
    else
        stringResource(type.titleResId)
    Box(
        modifier = modifier.background(
            color = type.backgroundColor,
            shape = RoundedCornerShape(type.cornerRadius)
        )
            .padding(
                vertical = type.verticalPadding,
                horizontal = type.horizontalPadding
            )

    ) {
        Text(
            text = contentString,
            style = type.textStyle.merge(color = type.textColor)
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
            type = SmallChipType.CATEGORY
        )
        SmallChip(
            type = SmallChipType.MATCH
        )
        SmallChip(
            type = SmallChipType.FINISHED
        )
        SmallChip(
            dynamicString = "3",
            type = SmallChipType.IMMINENT_D_DAY
        )
        SmallChip(
            dynamicString = "10",
            type = SmallChipType.D_DAY
        )
    }
}