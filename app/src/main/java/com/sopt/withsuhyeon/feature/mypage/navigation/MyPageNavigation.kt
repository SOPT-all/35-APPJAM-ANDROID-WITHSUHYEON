package com.sopt.withsuhyeon.feature.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.withsuhyeon.core.navigation.MainTabRoute
import com.sopt.withsuhyeon.core.navigation.Route
import com.sopt.withsuhyeon.feature.mypage.MyPageRoute

fun NavController.navigateToMyPage(navOptions: NavOptions) {
    navigate(MainTabRoute.MyPage, navOptions)
}

fun NavController.navigateToBlockUserFromMyPage() {
    navigate(Route.BlockUser)
}

fun NavController.navigateToOnBoarding() {
    navigate(Route.OnBoarding)
}

fun NavGraphBuilder.myPageNavGraph(
    padding: PaddingValues,
    onNavigateToBlockUser: () -> Unit,
    onNavigateToOnboarding: () -> Unit
) {
    composable<MainTabRoute.MyPage> {
        MyPageRoute(
            padding,
            navigateToBlockUser = onNavigateToBlockUser,
            navigateToOnboarding = onNavigateToOnboarding
        )
    }
}