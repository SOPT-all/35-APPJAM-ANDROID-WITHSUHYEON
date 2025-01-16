package com.sopt.withsuhyeon.core.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.Grey500
import com.sopt.withsuhyeon.ui.theme.defaultWithSuhyeonTypography

@Composable
fun ShowButton(
    modifier: Modifier = Modifier,
    text: String = "보기",
    font: TextStyle = defaultWithSuhyeonTypography.body03_SB,
    color: Color = Grey500,
    onClick: () -> Unit,
) {
    Text(
        modifier = modifier
            .noRippleClickable(onClick)
            .drawBehind {
                val lineY = size.height
                drawLine(
                    color = color,
                    start = Offset(0f, lineY),
                    end = Offset(size.width, lineY),
                    strokeWidth = 1.dp.toPx()
                )
            },
        text = text,
        style = font,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
fun ShowButtonPreview(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.height(50.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ShowButton(
            onClick = {}
        )
    }
}