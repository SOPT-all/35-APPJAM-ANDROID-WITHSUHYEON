package com.sopt.withsuhyeon.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.ui.theme.Grey200
import com.sopt.withsuhyeon.ui.theme.Grey400
import com.sopt.withsuhyeon.ui.theme.Purple500
import com.sopt.withsuhyeon.ui.theme.White
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.defaultWithSuhyeonTypography

@Composable
fun SmallButton(
    text: String,
    color: Color,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    font: TextStyle = defaultWithSuhyeonTypography.body01_B,
    padding: Int = 14,
) {
    Text(
        modifier = modifier
            .width(160.dp)
            .height(56.dp)
            .background(
                backgroundColor,
                shape = RoundedCornerShape(size = 16.dp)
            )
            .padding(vertical = padding.dp),
        text = text,
        color = color,
        textAlign = TextAlign.Center,
        style = font
    )
}

@Preview
@Composable
fun SmallButtonPreview(
    modifier: Modifier = Modifier
) {
    WithSuhyeonTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = modifier
                .background(White)
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            SmallButton(
                text = "버튼",
                color = White,
                backgroundColor = Purple500,
            )
            SmallButton(
                text = "버튼",
                color = Grey400,
                backgroundColor = Grey200,
            )
        }
    }
}
