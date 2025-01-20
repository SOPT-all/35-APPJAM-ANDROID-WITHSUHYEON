package com.sopt.withsuhyeon.feature.findsuhyeon.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.KeyStorage.FEMALE
import com.sopt.withsuhyeon.core.util.KeyStorage.MALE
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun SmallSelectChip(
    modifier: Modifier = Modifier,
    text: String = FEMALE,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
    image : ImageVector = ImageVector.vectorResource(R.drawable.dummy_ellipse),
) {
    val borderColor =
        when {
            isSelected -> colors.Purple100
            else -> Color.Transparent
        }
    val textColor =
        when {
            isSelected -> colors.Purple600
            else -> colors.Grey400
        }
    val backgroundColor = when {
        isSelected -> colors.Purple50
        else -> colors.Grey25
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(20.dp),
            )
            .border(1.dp, borderColor, RoundedCornerShape(20.dp))
            .noRippleClickable(onClick)
            .padding(12.dp)
    ) {
        Icon(
            imageVector = image,
            contentDescription = stringResource(R.string.smallselectchip),
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            tint = Color.Unspecified
        )
        Text(
            text = text,
            style = typography.body02_SB.merge(color = textColor),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
@Preview(
    widthDp = 328
)
fun PreviewSmallSelectChip() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colors.White)
    ) {
        SmallSelectChip(
            text = MALE,
            isSelected = false,
            image =  ImageVector.vectorResource(R.drawable.img_boy_suma),
            modifier = Modifier.weight(1f)
        )
        SmallSelectChip(
            text = FEMALE,
            isSelected = true,
            image = ImageVector.vectorResource(R.drawable.img_girl_suma),
            modifier = Modifier.weight(1f)
        )
    }
}