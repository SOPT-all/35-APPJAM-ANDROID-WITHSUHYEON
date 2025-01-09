package com.sopt.withsuhyeon.feature.findsuhyeon.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.withsuhyeon.core.navigation.MainTabRoute
import com.sopt.withsuhyeon.feature.findsuhyeon.FindSuhyeonRoute

fun NavController.navigateToFindSuhyeon(navOptions: NavOptions) {
    navigate(MainTabRoute.FindSuhyeon, navOptions)
}

fun NavGraphBuilder.findSuhyeonNavGraph(
    padding: PaddingValues
) {
    composable<MainTabRoute.FindSuhyeon> {
        FindSuhyeonRoute(padding)
    }
}