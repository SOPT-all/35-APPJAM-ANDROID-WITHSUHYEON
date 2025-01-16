package com.sopt.withsuhyeon.feature.gallery

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.card.GalleryMainCardItem
import com.sopt.withsuhyeon.core.component.chip.NewCategoryChip
import com.sopt.withsuhyeon.core.component.floatingbutton.AnimatedAddPostButton
import com.sopt.withsuhyeon.core.component.topbar.MainTopNavBar

@Composable
fun GalleryRoute(
    padding: PaddingValues,
    viewModel: GalleryViewModel = hiltViewModel()
) {
    GalleryScreen(
        padding = padding,
        viewModel = viewModel
    )
}
@SuppressLint("UnrememberedMutableState")
@Composable
private fun GalleryScreen(
    padding: PaddingValues,
    viewModel: GalleryViewModel
) {
    val lazyGridState = rememberLazyGridState()

    val isScrolled by remember {
        derivedStateOf { lazyGridState.firstVisibleItemScrollOffset > 0 }
    }

    val categoryRowHeight by animateDpAsState(
        targetValue = if (isScrolled) 40.dp else 100.dp,
        animationSpec = tween(durationMillis = 300), label = "Category Row Height"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            val total = stringResource(R.string.gallery_main_category_chip_total)
            var selectedCategory by remember { mutableStateOf(total) }
            val categories by viewModel.categories.collectAsState()
            val items by viewModel.items.collectAsState()

            MainTopNavBar(stringResource(R.string.gallery_top_nav_bar_title))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(categoryRowHeight)
                    .padding(horizontal = 16.dp)
            ) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(categories) { category ->
                        NewCategoryChip(
                            category = category,
                            scrollOffset = lazyGridState.firstVisibleItemScrollOffset.toFloat(),
                            isSelected = selectedCategory == category,
                            onClick = { selectedCategory = category }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(16.dp))

            LazyVerticalGrid(
                state = lazyGridState,
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items.size) { index ->
                    val (text, image) = items[index]
                    GalleryMainCardItem(
                        text = text,
                        image = image,
                        modifier = Modifier
                    )
                }
            }
        }

        AnimatedAddPostButton(
            text = stringResource(R.string.btn_add_gallery_post),
            lazyGridState = lazyGridState,
            modifier = Modifier
                .align(BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp),
            onClick = { }
        )
    }
}