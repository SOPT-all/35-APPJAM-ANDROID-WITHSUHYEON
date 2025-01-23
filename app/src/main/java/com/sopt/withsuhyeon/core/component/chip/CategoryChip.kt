package com.sopt.withsuhyeon.core.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun CategoryChip(
    imageUrl: String,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val chipBackgroundColor = if (isSelected) colors.Purple50 else colors.White
    val chipBorderColor = if (isSelected) colors.Purple100 else colors.Grey100
    val chipTextColor = if (isSelected) colors.Purple600 else colors.Grey700

    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = chipBorderColor,
                shape = RoundedCornerShape(37.dp)
            )
            .background(color = chipBackgroundColor, shape = RoundedCornerShape(37.dp))
            .padding(start = 12.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
            .noRippleClickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(18.dp)
        )

        Text(
            text = text,
            color = chipTextColor,
            style = typography.body03_SB
        )
    }
}