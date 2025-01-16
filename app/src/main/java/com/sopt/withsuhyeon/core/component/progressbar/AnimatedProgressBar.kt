package com.sopt.withsuhyeon.core.component.progressbar

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun AnimatedProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = colors.Grey100
    val progressColor = colors.Purple500

    val animatedProgress by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = stringResource(R.string.label_progressbar_animation)
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

@Preview(showBackground = true)
@Composable
fun PreviewAnimatedProgressBar() {
    var value by remember { mutableStateOf(0.1f) }
    Column {
        Button(
            onClick = {value += 0.1f}
        ) { }
        AnimatedProgressBar(value)
        AnimatedProgressBar(0.33f)
        AnimatedProgressBar(0.5f)
        AnimatedProgressBar(0.66f)
        AnimatedProgressBar(0.83f)
        AnimatedProgressBar(1f)
    }
}