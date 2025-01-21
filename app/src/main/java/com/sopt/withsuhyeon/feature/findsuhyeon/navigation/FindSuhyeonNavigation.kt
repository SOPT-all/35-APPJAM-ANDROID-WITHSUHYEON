package com.sopt.withsuhyeon.feature.findsuhyeon.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.sopt.withsuhyeon.core.navigation.MainTabRoute
import com.sopt.withsuhyeon.core.navigation.Route
import com.sopt.withsuhyeon.feature.findsuhyeon.FindSuhyeonUploadDetailRoute
import com.sopt.withsuhyeon.feature.findsuhyeon.FindSuhyeonPostRoute
import com.sopt.withsuhyeon.feature.findsuhyeon.FindSuhyeonRoute
import com.sopt.withsuhyeon.feature.findsuhyeon.FindSuhyeonUploadRoute
import com.sopt.withsuhyeon.feature.findsuhyeon.viewmodel.FindSuhyeonUploadViewModel

fun NavController.navigateToFindSuhyeon(navOptions: NavOptions) {
    navigate(MainTabRoute.FindSuhyeon, navOptions)
}
fun NavController.navigateToFindSuhyeonUpload() {
    navigate(Route.FindSuhyeonUpload)
}
fun NavController.navigateToFindSuhyeonUploadDetail() {
    navigate(Route.FindSuhyeonUploadDetail)
}
fun NavController.navigateToFindSuhyeonPost(id: Long?) {
    navigate(Route.FindSuhyeonPost(id))
}

fun NavGraphBuilder.findSuhyeonNavGraph(
    padding: PaddingValues,
    onNavigateToFindSuhyeon: () -> Unit,
    onNavigateToFindSuhyeonPost: (Long) -> Unit,
    onNavigateToFindSuheyonUpload: () -> Unit,
    onNavigateToFindSuheyonUploadDetail: () -> Unit,
    getBackStackUploadViewModel: @Composable (NavBackStackEntry) -> FindSuhyeonUploadViewModel
) {
    composable<MainTabRoute.FindSuhyeon> {
        FindSuhyeonRoute(
            padding,
            navigateToUpload = onNavigateToFindSuheyonUpload,
            navigateUp = onNavigateToFindSuhyeon,
            navigateToPost = onNavigateToFindSuhyeonPost
        )
    }
    composable<Route.FindSuhyeonUpload> {
        FindSuhyeonUploadRoute(
            padding,
            navigateUp = onNavigateToFindSuhyeon,
            navigateToUploadDetail = onNavigateToFindSuheyonUploadDetail,
            viewModel = hiltViewModel()
        )
    }
    composable<Route.FindSuhyeonUploadDetail> {
        FindSuhyeonUploadDetailRoute(
            padding = padding,
            navigateUp = onNavigateToFindSuhyeon,
            navigateToFindSuhyeon = onNavigateToFindSuhyeon,
            viewModel = getBackStackUploadViewModel(it)
        )
    }
    composable<Route.FindSuhyeonPost> { navBackStackEntry ->
        val id = navBackStackEntry.toRoute<Route.FindSuhyeonPost>().id
        FindSuhyeonPostRoute(
            padding = padding,
            id = id,
            navigateToFindSuhyeon = onNavigateToFindSuhyeon
        )
    }
}