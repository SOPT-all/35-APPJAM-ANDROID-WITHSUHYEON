package com.sopt.withsuhyeon.core.component.categoryrow

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.core.component.chip.GalleryMainFullCategoryChip
import com.sopt.withsuhyeon.core.component.chip.GalleryMainShortCategoryChip

@Composable
fun GalleryMainCategoryAnimatedRow(
    categories: List<String>,
    selectedCategory: String,
    isScrolled: Boolean,
    onCategorySelected: (String) -> Unit
) {
    val categoryRowHeight by animateDpAsState(
        targetValue = if (isScrolled) 40.dp else 86.dp,
        animationSpec = tween(durationMillis = 300), label = "Row Height"
    )

    val categoryRowAlpha by animateFloatAsState(
        targetValue = if (isScrolled) 1f else 0f,
        animationSpec = tween(durationMillis = 300), label = "Row Alpha"
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(categoryRowHeight)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categories) { category ->
            if (isScrolled) {
                GalleryMainShortCategoryChip(
                    text = category,
                    isSelected = selectedCategory == category,
                    onClick = { onCategorySelected(category) },
                    modifier = Modifier.alpha(categoryRowAlpha)
                )
            } else {
                GalleryMainFullCategoryChip(
                    text = category,
                    isSelected = selectedCategory == category,
                    onClick = { onCategorySelected(category) },
                    modifier = Modifier.alpha(1f - categoryRowAlpha)
                )
            }
        }
    }
}