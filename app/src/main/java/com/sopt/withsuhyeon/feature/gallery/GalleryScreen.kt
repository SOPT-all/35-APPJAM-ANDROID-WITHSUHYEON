package com.sopt.withsuhyeon.feature.gallery

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

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

}