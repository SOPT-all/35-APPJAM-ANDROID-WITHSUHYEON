package com.sopt.withsuhyeon.feature.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeRoute(
    padding: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreen(
        padding = padding
    )
}
@Composable
private fun HomeScreen(
    padding: PaddingValues
) {

}