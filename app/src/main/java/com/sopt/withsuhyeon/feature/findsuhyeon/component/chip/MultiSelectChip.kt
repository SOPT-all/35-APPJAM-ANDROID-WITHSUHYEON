package com.sopt.withsuhyeon.feature.findsuhyeon.component.chip

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_20_TO_24
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_25_TO_29
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_30_TO_34
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_35_TO_39
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_40
import com.sopt.withsuhyeon.core.util.KeyStorage.PHONE_CALL
import com.sopt.withsuhyeon.core.util.KeyStorage.TAKE_A_PHOTO
import com.sopt.withsuhyeon.core.util.KeyStorage.VIDEO_CALL
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun MultiSelectChip(
    modifier: Modifier = Modifier,
    text: String = TAKE_A_PHOTO,
    isSelected: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    image : Painter? = null,
) {
    val borderColor =
        when {
            image == null -> Color.Transparent
            isSelected -> colors.Purple100
            else -> Color.Transparent
        }
    val textColor =
        when {
            !enabled -> colors.Grey300
            isSelected -> colors.Purple600
            else -> colors.Grey400
        }
    val backgroundColor = when {
        !enabled -> colors.Grey100
        isSelected -> colors.Purple50
        else -> colors.Grey25
    }
    val checkIconColor =
        when {
            isSelected -> colors.Purple400
            else -> Color.Transparent
        }
    val startPadding = when {
        image == null -> 16.dp
        else -> 0.dp
    }
    val verticalPadding = when {
        image == null -> 20.dp
        else -> 8.dp
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        modifier = modifier.background(
                color = backgroundColor,
                shape = RoundedCornerShape(20.dp),
            )
            .border(1.dp, borderColor, RoundedCornerShape(20.dp))
            .padding(vertical = verticalPadding, horizontal =  8.dp)
            .noRippleClickable(onClick)
    ) {
        if(image != null) {
            Image(
                painter = image,
                contentDescription = "MultiSelectChip",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(48.dp)
                    .clip(CircleShape)
            )
        }
        Text(
            text = text,
            style = typography.body02_SB.merge(color = textColor),
            modifier = Modifier.padding(start = startPadding)
                .weight(1f)
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_check),
            tint = checkIconColor,
            contentDescription = "Check",
            modifier = Modifier.padding(end = 8.dp)
                .size(24.dp)
        )
    }
}

@Composable
@Preview(
    widthDp = 800,
)
fun PreviewMultiSelectChip() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.background(colors.White)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            MultiSelectChip(
                text = TAKE_A_PHOTO,
                isSelected = false,
                enabled = true,
                image =  painterResource(R.drawable.dummy_ellipse)
            )
            MultiSelectChip(
                text = VIDEO_CALL,
                isSelected = true,
                enabled = true,
                image =  painterResource(R.drawable.dummy_ellipse)
            )
            MultiSelectChip(
                text = PHONE_CALL,
                isSelected = false,
                enabled = false,
                image =  painterResource(R.drawable.dummy_ellipse)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            MultiSelectChip(
                text = AGE_20_TO_24,
                isSelected = false
            )
            MultiSelectChip(
                text = AGE_25_TO_29,
                isSelected = true
            )
            MultiSelectChip(
                text = AGE_30_TO_34,
                isSelected = false
            )
            MultiSelectChip(
                text = AGE_35_TO_39,
                isSelected = false
            )
            MultiSelectChip(
                text = AGE_40,
                isSelected = false
            )
        }
    }
}