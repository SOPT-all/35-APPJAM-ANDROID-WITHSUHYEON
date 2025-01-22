package com.sopt.withsuhyeon.feature.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.withsuhyeon.core.navigation.MainTabRoute
import com.sopt.withsuhyeon.feature.home.HomeRoute

fun NavController.navigateToHome(navOptions: NavOptions) {
    navigate(MainTabRoute.Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    navigateToGallery: () -> Unit,
    navigateToGalleryWithCategory: (String) -> Unit,
    navigateToPost: (Long) -> Unit
) {
    composable<MainTabRoute.Home> {
        HomeRoute(
            padding = padding,
            navigateToGallery = navigateToGallery,
            navigateToGalleryWithCategory = navigateToGalleryWithCategory,
            navigateToPost = navigateToPost
        )
    }
}