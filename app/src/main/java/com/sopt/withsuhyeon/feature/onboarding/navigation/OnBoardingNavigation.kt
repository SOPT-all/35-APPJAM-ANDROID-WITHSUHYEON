package com.sopt.withsuhyeon.feature.onboarding.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.withsuhyeon.feature.onboarding.TermsOfUseRoute
import com.sopt.withsuhyeon.core.navigation.Route.TermsOfUse as TermsOfUseRoute

fun NavController.navigateToTermsOfUse() {
    navigate(TermsOfUseRoute)
}

fun NavGraphBuilder.termsOfUseNavGraph(
    padding: PaddingValues,
    onNavigateToNext: () -> Unit
) {
    composable<TermsOfUseRoute> {
        TermsOfUseRoute(
            padding = padding,
            navigateToNext = onNavigateToNext
        )
    }
}