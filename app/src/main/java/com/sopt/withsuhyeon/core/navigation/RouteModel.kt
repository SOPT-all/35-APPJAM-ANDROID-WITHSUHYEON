package com.sopt.withsuhyeon.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object OnBoarding : Route
    @Serializable
    data object GalleryUpload : Route
}

sealed interface MainTabRoute : Route {
    @Serializable
    data object Home : MainTabRoute
    @Serializable
    data object FindSuhyeon : MainTabRoute

    @Serializable
    data object Gallery : MainTabRoute

    @Serializable
    data object Chat : MainTabRoute

    @Serializable
    data object MyPage : MainTabRoute
}