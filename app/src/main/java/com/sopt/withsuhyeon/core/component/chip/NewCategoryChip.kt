package com.sopt.withsuhyeon.core.component.chip

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
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
    val nospace = category.replace(" ", "")
    val afterTextWidth = remember { (nospace.length * 15).toFloat() }

    var iconSize by remember { mutableStateOf(36f) }
    var circleWidth by remember { mutableStateOf(60f) }
    var circleHeight by remember { mutableStateOf(60f) }
    var insideTextOpacity by remember { mutableStateOf(-5f) }
    var outsideTextOpacity by remember { mutableStateOf(1f) }
    var textWidth by remember { mutableStateOf(0f) }
    var textHeight by remember { mutableStateOf(26f) }
    var allTextWidth by remember { mutableStateOf(40f) }
    var allTextOpacity by remember { mutableStateOf(1f) }

    // 스크롤 변경 감지 시 애니메이션 적용
    LaunchedEffect(scrollOffset) {
        val progress = (-scrollOffset / 100f).coerceIn(0f, 1f)
        insideTextOpacity = -5f + (progress * 6)
        outsideTextOpacity = 1f - (progress * 1.17f)
        allTextOpacity = 1f - (progress * 3)
        allTextWidth = 40f - (progress * 40f)
        textWidth = (afterTextWidth * progress)
        circleHeight = 60f - (23 * progress)
        iconSize = 36f - (18 * progress)
        textHeight = 26f - (progress * 26)

        circleWidth = if (category == "전체") {
            60f + ((afterTextWidth - 31) * progress)
        } else {
            60f + ((afterTextWidth - 18) * progress)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(width = circleWidth.dp, height = circleHeight.dp)
                .background(
                    color = if (isSelected) colors.Purple500 else colors.Grey50,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (category == "전체") {
                    Text(
                        text = "ALL",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) colors.White else colors.Grey200,
                        modifier = Modifier
                            .width(allTextWidth.dp)
                            .alpha(allTextOpacity)
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_gallery),
                        contentDescription = null,
                        tint = colors.Grey300,
                        modifier = Modifier
                            .size(iconSize.dp)
                    )
                }

                Text(
                    text = category,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (isSelected) Color.White else Color(0xFF757575),
                    maxLines = 1,
                    modifier = Modifier
                        .width(textWidth.dp)
                        .alpha(insideTextOpacity)
                )
            }
        }

        Text(
            text = category,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) colors.Purple600 else colors.Grey400,
            modifier = Modifier
                .height(textHeight.dp)
                .alpha(outsideTextOpacity)
        )
    }

}

@Preview
@Composable
private fun NewCategoryChipPreview() {
    WithSuhyeonTheme {
        NewCategoryChip("바다", 0f, true, {})
    }
}