package com.sopt.withsuhyeon.feature.gallery.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.withsuhyeon.core.navigation.MainTabRoute
import com.sopt.withsuhyeon.core.navigation.Route
import com.sopt.withsuhyeon.feature.gallery.GalleryRoute
import com.sopt.withsuhyeon.feature.gallery.GalleryUploadRoute

fun NavController.navigateToGallery(navOptions: NavOptions) {
    navigate(MainTabRoute.Gallery, navOptions)
}

fun NavGraphBuilder.galleryNavGraph(
    padding: PaddingValues,
    navController: NavController
) {
    composable<MainTabRoute.Gallery> {
        GalleryRoute(
            padding = padding,
            navController = navController
        )
    }
    composable<Route.GalleryUpload> {
        GalleryUploadRoute(padding)
    }
}