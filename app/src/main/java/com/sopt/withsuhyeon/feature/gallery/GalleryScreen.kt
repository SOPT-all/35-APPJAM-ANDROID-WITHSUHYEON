package com.sopt.withsuhyeon.feature.gallery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.core.component.categoryrow.GalleryMainFullCategoryRow
import com.sopt.withsuhyeon.core.component.categoryrow.GalleryMainShortCategoryRow
import com.sopt.withsuhyeon.core.component.floatingbutton.AddGalleryPostButton

@Composable
fun GalleryRoute(
    padding: PaddingValues,
    viewModel: GalleryViewModel = hiltViewModel()
) {
    GalleryScreen(
        padding = padding
    )
}
@Composable
private fun GalleryScreen(
    padding: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            var selectedCategory by remember { mutableStateOf("전체") }
            val categories = listOf("전체", "바다", "학교", "파티룸", "엠티", "수현", "이랑", "합숙")

            GalleryMainFullCategoryRow(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = { category ->
                    selectedCategory = category
                }
            )

            Spacer(modifier = Modifier.padding(6.dp))

            GalleryMainShortCategoryRow(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = { category ->
                    selectedCategory = category
                }
            )
        }

        AddGalleryPostButton(
            modifier = Modifier
                .align(BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp),
            onClick = { }
        )
    }
}