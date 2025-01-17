package com.sopt.withsuhyeon.core.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun SubLocationChip(
    locationName: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    val backgroundColor = if (isSelected)
        colors.Purple50
    else
        Color.Transparent

    val textColor = if (isSelected)
        colors.Purple500
    else
        colors.Grey500

    Box(
        modifier = modifier.padding(
            top = 4.dp,
            bottom = 4.dp,
            start = 8.dp
        )
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .noRippleClickable(onClick)
            .padding(
                vertical = 10.dp,
                horizontal = 12.dp
            )
    ) {
        Text(
            text = locationName,
            style = typography.body02_SB.merge(color = textColor)
        )
    }
}

@Preview
@Composable
fun PreviewSubLocationChip() {
    var isSelected by remember { mutableStateOf(false) }
    Column {
        SubLocationChip(
            locationName = "을지로/명동/중구/동대문",
            isSelected = isSelected,
            onClick = {
                isSelected = !isSelected
            }
        )
    }
}