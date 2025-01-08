package com.sopt.withsuhyeon.feature.onboarding.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.withsuhyeon.feature.onboarding.OnBoardingRoute
import com.sopt.withsuhyeon.core.navigation.Route.OnBoarding as OnBoardingRoute

fun NavController.navigateOnBoarding() {
    navigate(OnBoardingRoute)
}

fun NavGraphBuilder.onBoardingNavGraph(
    padding: PaddingValues,
    onNavigateToHome: () -> Unit
) {
    composable<OnBoardingRoute> {
        OnBoardingRoute(
            padding = padding,
            navigateToHome = onNavigateToHome
        )
    }
}