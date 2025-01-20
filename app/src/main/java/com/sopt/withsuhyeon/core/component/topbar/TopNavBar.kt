package com.sopt.withsuhyeon.core.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography
import com.sopt.withsuhyeon.ui.theme.defaultWithSuhyeonColors

@Composable
fun MainTopNavBar(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(top = 7.dp, bottom = 15.dp, start = 16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = text,
                style = typography.title03_B,
                color = colors.Black
            )
        }
    }
}

@Composable
fun SubTopNavBar(
    text: String,
    btnIcon: Painter,
    isTextVisible: Boolean = true,
    isBtnVisible: Boolean = true,
    onCloseBtnClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.White)
            .padding(top = 8.dp, bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isTextVisible) {
                Box(
                    modifier = Modifier
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = text,
                        style = typography.body01_B,
                        color = colors.Black
                    )
                }
            }
        }

        if (isBtnVisible) {
            Icon(
                painter = btnIcon,
                contentDescription = stringResource(R.string.top_nav_bar_btn_icon_description),
                tint = colors.Black,
                modifier = Modifier
                    .align(CenterEnd)
                    .padding(top = 2.dp, bottom = 2.dp, end = 10.dp)
                    .noRippleClickable { onCloseBtnClicked() }
            )
        }
    }
}
@Preview
@Composable
fun PreviewSubTopBar(){
    SubTopNavBar(
        btnIcon = painterResource(R.drawable.ic_close),
        isTextVisible = false,
        isBtnVisible = true,
        onCloseBtnClicked = { },
        modifier = Modifier.drawBehind {
            val borderThickness = 1.dp.toPx()
            drawLine(
                color = defaultWithSuhyeonColors.Grey200,
                start = Offset(0f, size.height),
                end = Offset(size.width, size.height),
                strokeWidth = borderThickness
            )
        },
        text = ""
    )
}