package com.sopt.withsuhyeon.core.component.progressbar

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun AnimatedProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    backgroundColor: Color = colors.Grey100,
    progressColor: Color = colors.Purple500,
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "ProgressBar Animation"
    )
    Box(
        modifier = modifier
            .background(
                color = colors.White
            )
            .padding(16.dp)
            .fillMaxWidth()
            .height(8.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(fraction = animatedProgress)
                .fillMaxHeight()
                .background(
                    color = progressColor,
                    shape = RoundedCornerShape(8.dp)
                )
        )
    }
}

@Composable
fun AnimatedProgressDemo() {
    var progress by remember { mutableFloatStateOf(0f) }
    var currentStep by remember { mutableIntStateOf(0) }
    val totalSteps = 6

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "진행률: ${(progress * 100).toInt()}%",
            style = typography.body01_B
        )

        AnimatedProgressBar(progress = progress,)

        Button(
            onClick = {
                if (currentStep < totalSteps) {
                    currentStep += 1
                    progress += 1f/totalSteps
                }
            },
            enabled = currentStep < totalSteps
        ) {
            Text(text = "다음 단계")
        }

        Text(
            text = "$currentStep / $totalSteps",
            style = typography.body01_R
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnimatedProgressBar() {
    Column {
        AnimatedProgressBar(0.16f)
        AnimatedProgressBar(0.33f)
        AnimatedProgressBar(0.5f)
        AnimatedProgressBar(0.66f)
        AnimatedProgressBar(0.83f)
        AnimatedProgressBar(1f)
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewAnimatedProgressBarDemo() {
    AnimatedProgressDemo()
}