package com.sopt.withsuhyeon.core.component.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.util.price.toDecimalFormat
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun PostBottomBar(
    price: Int,
    isMyPost: Boolean,
    isDisabled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val borderColor = colors.Grey100
    val buttonText = if(isMyPost)
        stringResource(R.string.post_bottom_bar_in_progress_chating)
    else
        stringResource(R.string.post_bottom_bar_start_chating)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
        modifier = modifier
            .fillMaxWidth()
            .height(106.dp)
            .drawBehind {
                val borderThickness = 1.dp.toPx()
                drawLine(
                    color = borderColor,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = borderThickness
                )
            }
            .background(
                color = colors.White,
            )
            .padding(
                top = 16.dp,
                bottom = 34.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) {
        Column(
        ) {
            Text(
                text = stringResource(R.string.find_suhyeon_price),
                style = typography.caption01_SB.merge(colors.Grey500)
            )
            Text(
                text = "${price.toDecimalFormat()}원",
                style = typography.body02_B.merge(colors.Grey900)
            )
        }
        LargeButton(
            text = buttonText,
            isDisabled = if(isMyPost) false else isDisabled,
            isDownloadBtn = false,
            modifier = modifier.weight(1f),
            font = typography.body01_B,
            onClick = onClick
        )
    }
}

@Preview
@Composable
fun PreviewPostBottomBar() {
    var isDisabled by remember { mutableStateOf(false) }
    PostBottomBar(
        price = 50000,
        isMyPost = false,
        isDisabled = isDisabled,
        onClick = {
            isDisabled = !isDisabled
        }
    )
}