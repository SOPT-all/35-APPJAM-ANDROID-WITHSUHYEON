package com.sopt.withsuhyeon.core.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun BasicButtonForTextField(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isPrimary: Boolean = false
) {
    val backgroundColor = when {
        isPrimary && enabled -> colors.Purple500
        isPrimary && !enabled -> colors.Grey200
        !isPrimary && enabled -> colors.Purple50
        else -> colors.Grey100
    }

    val textColor = when {
        isPrimary && enabled -> colors.White
        isPrimary && !enabled -> colors.Grey400
        !isPrimary && enabled -> colors.Purple500
        else -> colors.Grey300
    }

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 10.dp, vertical = 9.dp)
            .noRippleClickable(onClick)
    ) {
        Text(
            text = text,
            style = typography.caption01_B.merge(color = textColor)
        )
    }
}

@Composable
@Preview
fun PreviewPrimaryBasicButtonForTextField() {
    var enabled by remember { mutableStateOf(true) }

    BasicButtonForTextField(
        text = "버튼이다",
        enabled = enabled,
        isPrimary = true,
        onClick = {
            enabled = !enabled
        },
        modifier = Modifier
    )
}

@Composable
@Preview
fun PreviewSecondaryBasicButtonForTextField() {
    var enabled by remember { mutableStateOf(true) }

    BasicButtonForTextField(
        text = "버튼이다",
        enabled = enabled,
        isPrimary = false,
        onClick = {
            enabled = !enabled
        },
        modifier = Modifier
    )
}