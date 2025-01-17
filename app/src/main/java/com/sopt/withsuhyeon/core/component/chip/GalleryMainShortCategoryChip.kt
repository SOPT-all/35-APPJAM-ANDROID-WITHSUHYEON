package com.sopt.withsuhyeon.core.component.chip

import androidx.compose.foundation.background
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
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun GalleryMainShortCategoryChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val chipBackgroundColor = if (isSelected) colors.Purple500 else colors.Grey50
    val chipTextColor = if (isSelected) colors.White else colors.Grey700

    Row(
        modifier = modifier
            .background(color = chipBackgroundColor, shape = RoundedCornerShape(37.dp))
            .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
            .noRippleClickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (text != "전체") {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(colors.Grey200),
                contentAlignment = Alignment.Center
            ) {}
        }

        Text(
            text = text,
            color = chipTextColor,
            style = typography.body03_SB
        )
    }
}