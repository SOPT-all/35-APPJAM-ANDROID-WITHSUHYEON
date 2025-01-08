package com.sopt.withsuhyeon.feature.onboarding

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun OnBoardingRoute(
    padding: PaddingValues,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    OnBoardingScreen()
}
@Composable
fun OnBoardingScreen(
) {

}