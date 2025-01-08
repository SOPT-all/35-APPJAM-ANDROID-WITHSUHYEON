package com.sopt.withsuhyeon.feature.findsuhyeon

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FindSuhyeonRoute(
    padding: PaddingValues,
    viewModel: FindSuhyeonViewModel = hiltViewModel()
) {
    FindSuhyeonScreen(
        padding = padding
    )
}
@Composable
private fun FindSuhyeonScreen(
    padding: PaddingValues
) {

}