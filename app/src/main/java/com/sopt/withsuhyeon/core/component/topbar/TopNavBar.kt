package com.sopt.withsuhyeon.core.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun MainTopNavBar(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(colors.Purple100)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(top = 4.dp, start = 16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = text,
                style = typography.body01_SB,
                color = colors.Black
            )
        }
    }
}

@Composable
fun SubTopNavBar(
    text: String,
    onCloseBtnClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(colors.Purple25)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    style = typography.body01_SB,
                    color = colors.Black
                )
            }
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = "Close Button",
            tint = colors.Black,
            modifier = Modifier
                .align(CenterEnd)
                .padding(end = 16.dp)
                .noRippleClickable { onCloseBtnClicked() }
        )
    }
}