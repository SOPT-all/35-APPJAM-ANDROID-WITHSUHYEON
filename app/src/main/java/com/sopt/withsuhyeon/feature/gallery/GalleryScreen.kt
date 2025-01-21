package com.sopt.withsuhyeon.feature.gallery

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.runtime.LaunchedEffect
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
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun GalleryRoute(
    padding: PaddingValues,
    navigateToGalleryUpload: () -> Unit,
    navigateToGalleryPostDetail: () -> Unit,
    viewModel: GalleryViewModel = hiltViewModel()
) {
    GalleryScreen(
        padding = padding,
        onFloatingButtonClick = {
            navigateToGalleryUpload()
        },
        onGalleryCardItemClick = {
            navigateToGalleryPostDetail()
        }
    )
}
@SuppressLint("UnrememberedMutableState")
@Composable
private fun GalleryScreen(
    padding: PaddingValues,
    onFloatingButtonClick: () -> Unit,
    onGalleryCardItemClick: () -> Unit,
    viewModel: GalleryViewModel = hiltViewModel()
) {
    val lazyGridState = rememberLazyGridState()

    val isScrolled by remember {
        derivedStateOf { lazyGridState.firstVisibleItemScrollOffset > 0 }
    }

    val categoryRowHeight by animateDpAsState(
        targetValue = if (isScrolled) 40.dp else 100.dp,
        animationSpec = tween(durationMillis = 300), label = "Category Row Height"
    )

    val categories by viewModel.categories.collectAsState()
    val galleries by viewModel.galleries.collectAsState()
    var selectedCategory by remember { mutableStateOf("전체") }

    LaunchedEffect(Unit) {
        viewModel.getGalleryCategories()
        viewModel.getGalleryTotal("전체")
    }

    LaunchedEffect(selectedCategory) {
        selectedCategory?.let {
            viewModel.getGalleryTotal(it)
        }
    }

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
            MainTopNavBar(stringResource(R.string.gallery_top_nav_bar_title))

            Spacer(modifier = Modifier.fillMaxWidth().height(16.dp).background(colors.White))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(categoryRowHeight)
                    .background(color = colors.White)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    item {
                        NewCategoryChip(
                            imageUrl = "https://example.com/default-image.jpg",
                            category = "전체", // 기본 카테고리 이름 (예: "전체")
                            scrollOffset = lazyGridState.firstVisibleItemScrollOffset.toFloat(),
                            isSelected = selectedCategory == "전체",
                            onClick = {
                                selectedCategory = "전체" // 기본 카테고리를 선택했을 때 처리
                            }
                        )
                    }
                    items(categories) { category ->
                        NewCategoryChip(
                            imageUrl = category.imageUrl,
                            category = category.category,
                            scrollOffset = lazyGridState.firstVisibleItemScrollOffset.toFloat(),
                            isSelected = selectedCategory == category.category,
                            onClick = {
                                selectedCategory = category.category
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.fillMaxWidth().height(16.dp).background(colors.White))

            LazyVerticalGrid(
                state = lazyGridState,
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(galleries.size) { index ->
                    val gallery = galleries[index]
                    GalleryMainCardItem(
                        text = gallery.title,
                        image = gallery.imageUrl,
                        onClick = onGalleryCardItemClick,
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
            onClick = onFloatingButtonClick
        )
    }
}