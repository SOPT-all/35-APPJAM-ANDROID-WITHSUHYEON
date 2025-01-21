package com.sopt.withsuhyeon.feature.onboarding

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

@Composable
fun SelectLocationRoute(
    padding: PaddingValues,
    navigateToNext: () -> Unit
) {
    SelectLocationScreen(
        padding = padding,
        onButtonClick = navigateToNext
    )
}

@Composable
fun SelectLocationScreen(
    padding: PaddingValues,
    onButtonClick: () -> Unit
) {

}