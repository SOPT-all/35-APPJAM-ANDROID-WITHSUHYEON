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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.Grey100
import com.sopt.withsuhyeon.ui.theme.Grey500
import com.sopt.withsuhyeon.ui.theme.Purple500
import com.sopt.withsuhyeon.ui.theme.Red01
import com.sopt.withsuhyeon.ui.theme.White
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.defaultWithSuhyeonTypography

@Composable
fun SmallButton(
    text: String,
    type: String? = null,
    modifier: Modifier = Modifier,
    font: TextStyle = defaultWithSuhyeonTypography.body01_B,
    padding: Int = 14,
    onClick: () -> Unit
) {
    val backgroundColor: Color
    val textColor: Color

    when (type) {
        "alert" -> {
            backgroundColor = Red01
            textColor = White
        }

        "disabled" -> {
            backgroundColor = Grey100
            textColor = Grey500
        }

        else -> {
            backgroundColor = Purple500
            textColor = White
        }
    }
    Text(
        modifier = modifier
            .width(160.dp)
            .height(56.dp)
            .background(
                backgroundColor,
                shape = RoundedCornerShape(size = 16.dp)
            )
            .padding(vertical = padding.dp)
            .noRippleClickable(onClick),
        text = text,
        color = textColor,
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
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(White)
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            SmallButton(
                text = "버튼",
                type = "alert",
                onClick = {}
            )
            SmallButton(
                text = "버튼",
                type = "disabled",
                onClick = {}
            )
            SmallButton(
                text = "버튼",
                onClick = {}
            )
        }
    }
}
