package com.sopt.withsuhyeon.core.component.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun ChatBubble(
    message: String,
    time: String,
    isSentByUser: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = if (isSentByUser) Arrangement.End else Arrangement.Start
    ) {
        if (!isSentByUser) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(colors.Grey200, shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        if (!isSentByUser) {
            Box(
                modifier = Modifier
                    .background(
                        color = colors.Grey50,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .widthIn(max = 230.dp)
            ) {
                Text(
                    text = message,
                    style = typography.body03_R,
                    color = colors.Black,
                    modifier = modifier.align(Alignment.BottomStart)
                )
            }

            Text(
                text = time,
                style = typography.caption02_R,
                color = colors.Grey400,
                modifier = Modifier.padding(start = 6.dp)
            )
        } else {
            Text(
                text = time,
                style = typography.caption02_R,
                color = colors.Grey400,
                modifier = Modifier.padding(end = 6.dp)
            )

            Box(
                modifier = Modifier
                    .background(
                        color = colors.Purple400,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .widthIn(max = 260.dp)
            ) {
                Text(
                    text = message,
                    style = typography.body03_R,
                    color = colors.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun ChatBubblePreview() {
    WithSuhyeonTheme {
        ChatBubble("안녕안녕", "오후 04:01", false)
        ChatBubble("안녕안녕", "오후 04:01", true)
    }
}