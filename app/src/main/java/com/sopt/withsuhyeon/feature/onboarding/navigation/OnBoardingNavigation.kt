package com.sopt.withsuhyeon.feature.onboarding.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.withsuhyeon.core.navigation.MainTabRoute
import com.sopt.withsuhyeon.core.navigation.Route
import com.sopt.withsuhyeon.feature.mypage.MyPageRoute
import com.sopt.withsuhyeon.feature.onboarding.OnBoardingRoute
import com.sopt.withsuhyeon.feature.onboarding.OnBoardingScreen
import com.sopt.withsuhyeon.core.navigation.Route.OnBoarding as OnBoardingRoute

fun NavController.navigateOnBoarding() {
    navigate(OnBoardingRoute)
}

fun NavGraphBuilder.onBoardingNavGraph(
    padding: PaddingValues
) {
    composable<OnBoardingRoute> {
        OnBoardingRoute(
            padding = padding
        )
    }
}