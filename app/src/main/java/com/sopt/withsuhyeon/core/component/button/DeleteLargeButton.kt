package com.sopt.withsuhyeon.core.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.White
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun DeleteLargeButton(
    text: String,
    isDownloadBtn: Boolean = true,
    modifier: Modifier = Modifier,
    font: TextStyle = typography.body01_B,
    onClick: () -> Unit,
) {
    val backgroundColor: Color
    val textColor: Color

    when (isDownloadBtn) {
        true -> {
            backgroundColor = colors.Grey50
            textColor = colors.Red01
        }

        else -> {
            backgroundColor = colors.Grey50
            textColor = colors.Grey500
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(backgroundColor, shape = RoundedCornerShape(size = 16.dp))
            .padding(horizontal = 16.dp)
            .noRippleClickable(onClick),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (isDownloadBtn) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                )
            }
            Text(
                text = text,
                color = textColor,
                textAlign = TextAlign.Center,
                style = font
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteLargeButtonPreview(
    modifier: Modifier = Modifier
) {
    WithSuhyeonTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(White)
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            DeleteLargeButton(
                text = "버튼",
                onClick = {}
            )
            DeleteLargeButton(
                text = "닫기",
                onClick = {},
                isDownloadBtn = false
            )
        }
    }
}