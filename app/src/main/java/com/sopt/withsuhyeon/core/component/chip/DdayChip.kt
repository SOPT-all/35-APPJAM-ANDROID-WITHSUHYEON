package com.sopt.withsuhyeon.core.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun DdayChip(
    modifier: Modifier = Modifier,
    font: TextStyle = typography.caption02_B,
    color: Color = colors.Purple500,
    backgroundColor: Color = colors.Purple50,
    duration: Int,
) {
    Box(
        modifier = modifier
            .width(50.dp)
            .height(20.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(size = 8.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "D-${duration}",
            style = font,
            color = color
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChip(
    modifier: Modifier = Modifier
) {
    WithSuhyeonTheme {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(30.dp)
        ) {
            DdayChip(
                duration = 10
            )
        }
    }
}