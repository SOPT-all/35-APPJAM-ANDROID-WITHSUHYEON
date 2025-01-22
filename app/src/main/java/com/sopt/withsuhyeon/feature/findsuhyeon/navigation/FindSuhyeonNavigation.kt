package com.sopt.withsuhyeon.feature.findsuhyeon.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.withsuhyeon.core.navigation.MainTabRoute
import com.sopt.withsuhyeon.core.navigation.Route
import com.sopt.withsuhyeon.feature.findsuhyeon.FindSuhyeonUploadDetailRoute
import com.sopt.withsuhyeon.feature.findsuhyeon.FindSuhyeonPostRoute
import com.sopt.withsuhyeon.feature.findsuhyeon.FindSuhyeonRoute
import com.sopt.withsuhyeon.feature.findsuhyeon.FindSuhyeonUploadRoute

fun NavController.navigateToFindSuhyeon(navOptions: NavOptions) {
    navigate(MainTabRoute.FindSuhyeon, navOptions)
}
fun NavController.navigateToFindSuhyeonUpload() {
    navigate(Route.FindSuhyeonUpload)
}
fun NavController.navigateToFindSuhyeonUploadDetail() {
    navigate(Route.FindSuhyeonUploadDetail)
}
fun NavController.navigateToFindSuhyeonPost() {
    navigate(Route.FindSuhyeonPost)
}

fun NavGraphBuilder.findSuhyeonNavGraph(
    padding: PaddingValues,
    onNavigateToFindSuhyeon: () -> Unit,
    onNavigateToFindSuheyonUpload: () -> Unit,
    onNavigateToFindSuheyonUploadDetail: () -> Unit,
    onNavigateToFindSuhyeonPost: () -> Unit,
) {
    composable<MainTabRoute.FindSuhyeon> {
        FindSuhyeonRoute(
            padding,
            navigateToUpload = onNavigateToFindSuheyonUpload,
            navigateUp = onNavigateToFindSuhyeon,
        )
    }
    composable<Route.FindSuhyeonUpload> {
        FindSuhyeonUploadRoute(
            padding,
            navigateUp = onNavigateToFindSuhyeon,
            navigateToUploadDetail = onNavigateToFindSuheyonUploadDetail
        )
    }
    composable<Route.FindSuhyeonUploadDetail> {
        FindSuhyeonUploadDetailRoute(
            padding = padding,
            navigateUp = onNavigateToFindSuhyeon,
            navigateToMyPost = onNavigateToFindSuhyeonPost,
        )
    }
    composable<Route.FindSuhyeonPost> {
        FindSuhyeonPostRoute(
            padding = padding
        )
    }
}