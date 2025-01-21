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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun NewCategoryChip(
    imageUrl: String,
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
    var insideTextOpacity by remember { mutableStateOf(-2f) }
    var outsideTextOpacity by remember { mutableStateOf(1f) }
    var textWidth by remember { mutableStateOf(0f) }
    var textHeight by remember { mutableStateOf(20f) }
    var allTextWidth by remember { mutableStateOf(30f) }
    var allTextOpacity by remember { mutableStateOf(1f) }

    val chipSize by animateDpAsState(
        targetValue = if (scrollOffset > 0) 70.dp else 60.dp,
        animationSpec = tween(durationMillis = 300), label = "Box Size"
    )

    val boxSize by animateDpAsState(
        targetValue = if (scrollOffset > 0) 18.dp else 36.dp,
        animationSpec = tween(durationMillis = 300), label = "Box Size"
    )

    val chipTextColor by animateColorAsState(
        targetValue = if (isSelected) colors.Purple600 else colors.Grey400,
        animationSpec = tween(durationMillis = 300), label = "Chip Text Color"
    )

    LaunchedEffect(scrollOffset) {
        val progress = (-scrollOffset / 100f).coerceIn(0f, 1f)
        insideTextOpacity = -2f + (progress * 3)
        outsideTextOpacity = 1f - (progress * 0.8f)
        allTextOpacity = 1f - (progress * 2.5f)
        allTextWidth = 30f - (progress * 20f)
        textWidth = afterTextWidth * progress
        circleHeight = 60f - (15 * progress)
        iconSize = 36f - (12 * progress)
        textHeight = 20f - (progress * 10)

        circleWidth = if (category == "전체") {
            60f + ((afterTextWidth - 20) * progress)
        } else {
            60f + ((afterTextWidth - 15) * progress)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.noRippleClickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(width = chipSize, height = circleHeight.dp)
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
                        color = if (isSelected) Color.White else colors.Purple500,
                        modifier = Modifier
                            .width(allTextWidth.dp)
                            .alpha(allTextOpacity)
                    )
                } else {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .size(boxSize)
                            .background(colors.Grey200)
                    )
                }

                Text(
                    text = category,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = chipTextColor,
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
            color = if (isSelected) colors.Purple500 else colors.Grey400,
            modifier = Modifier
                .height(textHeight.dp)
                .alpha(outsideTextOpacity)
        )
    }
}