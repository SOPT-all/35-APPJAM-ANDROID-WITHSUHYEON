package com.sopt.withsuhyeon.feature.findsuhyeon.component

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.KeyStorage.PHONE_CALL
import com.sopt.withsuhyeon.core.util.KeyStorage.TAKE_A_PHOTO
import com.sopt.withsuhyeon.core.util.KeyStorage.VIDEO_CALL
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun MultiSelectChip(
    modifier: Modifier = Modifier,
    text: String = TAKE_A_PHOTO,
    isSelected: Boolean = false,
    enabled: Boolean = true,
) {
    val borderColor =
        when {
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
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        modifier = modifier.background(
                color = backgroundColor,
                shape = RoundedCornerShape(20.dp),
            )
            .border(1.dp, borderColor, RoundedCornerShape(20.dp))
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.dummy_ellipse),
            contentDescription = "MultiSelectChip",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(48.dp)
                .clip(CircleShape)
        )
        Text(
            text = text,
            style = typography.body02_SB.merge(color = textColor),
            modifier = Modifier.weight(1f)
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
@Preview
fun PreviewMultiSelectChip() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MultiSelectChip(
            text = TAKE_A_PHOTO,
            isSelected = false,
            enabled = true
        )
        MultiSelectChip(
            text = VIDEO_CALL,
            isSelected = true,
            enabled = true
        )
        MultiSelectChip(
            text = PHONE_CALL,
            isSelected = false,
            enabled = false
        )
    }
}