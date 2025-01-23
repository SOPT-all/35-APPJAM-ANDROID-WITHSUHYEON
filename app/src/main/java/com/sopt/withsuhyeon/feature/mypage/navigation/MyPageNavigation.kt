package com.sopt.withsuhyeon.feature.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.withsuhyeon.core.navigation.MainTabRoute
import com.sopt.withsuhyeon.core.navigation.Route
import com.sopt.withsuhyeon.feature.mypage.MyPageFavoriteLocationRoute
import com.sopt.withsuhyeon.feature.mypage.MyPagePostRoute
import com.sopt.withsuhyeon.feature.mypage.MyPageRoute
import com.sopt.withsuhyeon.feature.mypage.MyPageWithDrawRoute

fun NavController.navigateToMyPage(navOptions: NavOptions) {
    navigate(MainTabRoute.MyPage, navOptions)
}

fun NavController.navigateToBlockUserFromMyPage() {
    navigate(Route.BlockUser)
}

fun NavController.navigateToOnBoarding() {
    navigate(Route.OnBoarding)
}
fun NavController.navigateToMyPagePost() {
    navigate(Route.MyPagePost)
}
fun NavController.navigateToFavoriteLocation() {
    navigate(Route.MyPageFavoriteLocation)
}
fun NavController.navigateToWithdraw() {
    navigate(Route.MyPageWithdraw)
}

fun NavGraphBuilder.myPageNavGraph(
    padding: PaddingValues,
    onNavigateToBlockUser: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
    onNavigateToMyPagePost: () -> Unit,
    onNavigateToFavoriteLocation: () -> Unit,
    onNavigateToWithdraw: () -> Unit,
    onNavigateUp: () -> Unit
) {
    composable<MainTabRoute.MyPage> {
        MyPageRoute(
            padding,
            navigateToBlockUser = onNavigateToBlockUser,
            navigateToOnboarding = onNavigateToOnboarding,
            navigateToPost = onNavigateToMyPagePost,
            navigateToLocation = onNavigateToFavoriteLocation,
            navigateToWithdraw = onNavigateToWithdraw
        )
    }
    composable<Route.MyPageWithdraw> {
        MyPageWithDrawRoute(
            padding,
            onNavigateUp
        )
    }
    composable<Route.MyPagePost> {
        MyPagePostRoute(
            padding,
            onNavigateUp
        )
    }
    composable<Route.MyPageFavoriteLocation> {
        MyPageFavoriteLocationRoute(
            padding,
            onNavigateUp
        )
    }
}