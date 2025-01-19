package com.sopt.withsuhyeon.feature.onboarding

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

@Composable
fun SelectProfileRoute(
    padding: PaddingValues,
    navigateToNext: () -> Unit
) {
    SelectProfileScreen(
        padding = padding,
        onButtonClick = navigateToNext
    )
}

@Composable
fun SelectProfileScreen(
    padding: PaddingValues,
    onButtonClick: () -> Unit
) {

}