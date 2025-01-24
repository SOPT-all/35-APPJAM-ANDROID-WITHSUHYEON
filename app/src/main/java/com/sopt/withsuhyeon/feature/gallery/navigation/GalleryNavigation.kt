package com.sopt.withsuhyeon.feature.gallery.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.sopt.withsuhyeon.core.navigation.MainTabRoute
import com.sopt.withsuhyeon.core.navigation.Route
import com.sopt.withsuhyeon.feature.gallery.GalleryPostDetailRoute
import com.sopt.withsuhyeon.feature.gallery.GalleryRoute
import com.sopt.withsuhyeon.feature.gallery.GalleryUploadRoute

fun NavController.navigateToGallery(navOptions: NavOptions, category: String? = null) {
    navigate(MainTabRoute.Gallery(category), navOptions)
}

fun NavController.navigateToGalleryUpload() {
    navigate(Route.GalleryUpload)
}

fun NavController.navigateToGalleryPostDetail(galleryId: Long) {
    navigate(Route.GalleryPostDetail(galleryId))
}

fun NavGraphBuilder.galleryNavGraph(
    padding: PaddingValues,
    onNavigateToGalleryUpload: () -> Unit,
    onNavigateToGalleryPostDetail: (Long) -> Unit,
    onPopBackStackToGallery: () -> Unit
) {
    composable<MainTabRoute.Gallery> {
        GalleryRoute(
            padding = padding,
            navigateToGalleryUpload = onNavigateToGalleryUpload,
            navigateToGalleryPostDetail = { galleryId ->
                onNavigateToGalleryPostDetail(galleryId)
            }
        )
    }
    composable<Route.GalleryUpload> {
        GalleryUploadRoute(
            padding = padding,
            popBackStackToGallery = onPopBackStackToGallery
        )
    }
    composable<Route.GalleryPostDetail> { navBackStackEntry ->
        val galleryId = navBackStackEntry.toRoute<Route.GalleryPostDetail>().galleryId
        GalleryPostDetailRoute(
            padding = padding,
            galleryId = galleryId,
            popBackStackToGallery = onPopBackStackToGallery
        )
    }
}