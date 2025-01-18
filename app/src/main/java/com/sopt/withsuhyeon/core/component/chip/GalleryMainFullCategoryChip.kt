package com.sopt.withsuhyeon.core.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun GalleryMainFullCategoryChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .noRippleClickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(
                    color = if (isSelected) colors.Purple500 else colors.Grey50,
                    shape = RoundedCornerShape(30.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            if (text == "전체") {
                Text(
                    text = "ALL",
                    color = if (isSelected) colors.White else colors.Grey700,
                    style = typography.body03_B
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(
                            color = if (isSelected) colors.Grey300 else colors.White,
                            shape = RoundedCornerShape(12.dp)
                        )
                )
            }
        }

        Text(
            text = text,
            color = if (isSelected) colors.Purple600 else colors.Grey400,
            style = typography.body03_B,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}