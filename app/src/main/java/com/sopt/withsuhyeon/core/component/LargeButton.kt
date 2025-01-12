package com.sopt.withsuhyeon.core.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.ui.theme.Grey200
import com.sopt.withsuhyeon.ui.theme.Grey400
import com.sopt.withsuhyeon.ui.theme.Purple500
import com.sopt.withsuhyeon.ui.theme.White
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme

@Composable
fun LargeButton(
    isDisabled: Boolean = false,
    isDownload: Boolean = false,
    text: String,
    modifier: Modifier = Modifier,
    padding: Int = 14,
) {
    val backgroundColor: Color
    val textColor: Color

    when (isDisabled) {
        true -> {
            backgroundColor = Grey200
            textColor = Grey400
        }

        else -> {
            backgroundColor = Purple500
            textColor = White
        }
    }

    Box(
        modifier = modifier
            .width(328.dp)
            .height(56.dp)
            .background(backgroundColor, shape = RoundedCornerShape(size = 16.dp))
            .padding(padding.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (isDownload) {
                Image(
                    painter = painterResource(id = R.drawable.ic_download),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(textColor),
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                )
            }
            Text(
                text = text,
                color = textColor,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LargeButtonPreview(
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
            LargeButton(
                text = "버튼"
            )
            LargeButton(
                text = "버튼",
                isDisabled = true
            )
            LargeButton(
                isDownload = true,
                text = "버튼"
            )
            LargeButton(
                isDownload = true,
                isDisabled = true,
                text = "버튼"
            )
        }
    }
}