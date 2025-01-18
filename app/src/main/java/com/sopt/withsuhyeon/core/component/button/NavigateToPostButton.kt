package com.sopt.withsuhyeon.core.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun NavigateToPostButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(76.dp)
            .background(
                color = colors.Purple50,
                shape = RoundedCornerShape(size = 8.dp)
            )
            .padding(vertical = 9.dp)
            .noRippleClickable(onClick),
        contentAlignment = Alignment.Center
    )
    {
        Text(
            text = "게시물 보기",
            color = colors.Purple500,
            textAlign = TextAlign.Center,
            style = typography.caption01_B
        )
    }
}