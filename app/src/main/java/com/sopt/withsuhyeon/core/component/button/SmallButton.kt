package com.sopt.withsuhyeon.core.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.sopt.withsuhyeon.core.util.KeyStorage.ALERT_TYPE
import com.sopt.withsuhyeon.core.util.KeyStorage.DISABLED_TYPE
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
    onClick: () -> Unit
) {
    val backgroundColor: Color
    val textColor: Color

    when (type) {
        ALERT_TYPE -> {
            backgroundColor = Red01
            textColor = White
        }

        DISABLED_TYPE -> {
            backgroundColor = Grey100
            textColor = Grey500
        }

        else -> {
            backgroundColor = Purple500
            textColor = White
        }
    }
    Box(
        modifier = modifier
            .background(
                backgroundColor,
                shape = RoundedCornerShape(size = 16.dp)
            )
            .padding(
                vertical = 14.dp,
                horizontal = 64.dp
            )
            .noRippleClickable(onClick),
    )
    {
        Text(
            text = text,
            color = textColor,
            textAlign = TextAlign.Center,
            style = font
        )
    }
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
                .fillMaxWidth()
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
