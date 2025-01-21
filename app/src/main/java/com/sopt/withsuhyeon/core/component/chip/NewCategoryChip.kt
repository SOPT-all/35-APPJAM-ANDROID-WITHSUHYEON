package com.sopt.withsuhyeon.core.component.chip

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun NewCategoryChip(
    category: String,
    scrollOffset: Float,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // 스크롤 진행도 계산
    val progress = (-scrollOffset / 100f).coerceIn(0f, 1f)

    // 애니메이션되는 값들
    val iconSize by animateDpAsState(
        targetValue = if (scrollOffset > 0) 30.dp else 36.dp,
        animationSpec = tween(durationMillis = 300)
    )
    val chipBackgroundColor by animateColorAsState(
        targetValue = if (isSelected) colors.Purple500 else colors.Grey50,
        animationSpec = tween(durationMillis = 300)
    )
    val textWidth by animateDpAsState(
        targetValue = if (progress > 0) 30.dp else 0.dp,
        animationSpec = tween(durationMillis = 300)
    )
    val textOpacity by animateFloatAsState(
        targetValue = if (progress > 0) 1f else 0f,
        animationSpec = tween(durationMillis = 300)
    )
    val columnTextOpacity by animateFloatAsState(
        targetValue = if (progress > 0) 0f else 1f,
        animationSpec = tween(durationMillis = 300)
    )

    if (progress > 0) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .background(
                    color = if (isSelected) colors.Purple500 else colors.Grey50,
                    shape = CircleShape
                )
                .padding(start = 12.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                .noRippleClickable { onClick() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_gallery),
                contentDescription = null,
                tint = colors.Grey300,
                modifier = Modifier.size(iconSize)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = category,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (isSelected) Color.White else Color(0xFF757575),
                maxLines = 1,
                modifier = Modifier
                    .width(textWidth)
                    .alpha(textOpacity)
            )
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.noRippleClickable { onClick() }
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        color = if (isSelected) colors.Purple500 else colors.Grey50,
                        shape = CircleShape
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_gallery),
                    contentDescription = null,
                    tint = colors.Grey300,
                    modifier = Modifier.size(iconSize)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = category,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) colors.Purple600 else colors.Grey400,
                modifier = Modifier.alpha(columnTextOpacity)
            )
        }
    }
}



@Preview
@Composable
private fun NewCategoryChipPreview() {
    WithSuhyeonTheme {
        NewCategoryChip("바다", 0f, true, {})
    }
}