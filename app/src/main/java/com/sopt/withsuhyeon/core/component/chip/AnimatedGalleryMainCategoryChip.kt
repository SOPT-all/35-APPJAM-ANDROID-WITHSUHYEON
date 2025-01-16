package com.sopt.withsuhyeon.core.component.chip

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun AnimatedGalleryMainCategoryChip(
    text: String,
    isSelected: Boolean,
    isScrolled: Boolean,
    onClick: () -> Unit
) {
    val chipHeight by animateDpAsState(
        targetValue = if (isScrolled) 40.dp else 60.dp,
        animationSpec = tween(durationMillis = 300), label = "Chip Height"
    )

    val boxSize by animateDpAsState(
        targetValue = if (isScrolled) 18.dp else 36.dp,
        animationSpec = tween(durationMillis = 300), label = "Box Size"
    )

    val chipBackgroundColor by animateColorAsState(
        targetValue = if (isSelected) colors.Purple500 else colors.Grey50,
        animationSpec = tween(durationMillis = 300), label = "Chip Background Color"
    )

    val chipTextColor by animateColorAsState(
        targetValue = if (isScrolled && isSelected) colors.Purple600 else if (isScrolled) colors.Grey400 else Transparent,
        animationSpec = tween(durationMillis = 300), label = "Chip Text Color"
    )

    Row(
        modifier = Modifier
            .height(chipHeight)
            .background(color = chipBackgroundColor, shape = RoundedCornerShape(37.dp))
            .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
            .noRippleClickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(boxSize)
                .background(colors.Grey200),
            contentAlignment = Alignment.Center
        ) {}

        if (isScrolled) {
            Text(
                text = text,
                color = chipTextColor,
                style = typography.body03_SB
            )
        }
    }
}