package com.sopt.withsuhyeon.feature.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageCount: Int,
    currentPage: Int,
    activeColor: androidx.compose.ui.graphics.Color = colors.Black,
    inactiveColor: androidx.compose.ui.graphics.Color = colors.Grey400,
    indicatorSize: Dp = 8.dp,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until pageCount) {
            Box(
                modifier = Modifier
                    .size(indicatorSize)
                    .background(
                        color = if (i == currentPage) activeColor else inactiveColor,
                        shape = CircleShape
                    )
            )
            Spacer(Modifier.width(12.dp))
        }
    }
}
