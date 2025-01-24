package com.sopt.withsuhyeon.feature.splash

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.withsuhyeon.core.navigation.Route

fun NavGraphBuilder.splashNavGraph(
    onNavigateToHome: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
    padding: PaddingValues,
) {
    composable<Route.Splash> {
        SplashRoute(
            padding = padding,
            onNavigateToHome = onNavigateToHome,
            onNavigateToOnboarding = onNavigateToOnboarding
        )
    }
}
