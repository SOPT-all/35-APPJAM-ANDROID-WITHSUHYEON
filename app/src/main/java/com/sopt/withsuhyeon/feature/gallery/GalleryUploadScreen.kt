package com.sopt.withsuhyeon.feature.gallery

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GalleryUploadRoute(
    padding: PaddingValues,
    viewModel: GalleryViewModel = hiltViewModel()
) {
    GalleryUploadScreen(
        padding = padding
    )
}

@Composable
private fun GalleryUploadScreen(
    padding: PaddingValues
) {

}