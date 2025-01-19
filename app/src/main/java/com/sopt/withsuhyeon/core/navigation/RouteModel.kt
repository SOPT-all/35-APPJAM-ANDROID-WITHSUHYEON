package com.sopt.withsuhyeon.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object TermsOfUse : Route
    @Serializable
    data object PhoneNumberAuth : Route
    @Serializable
    data object NickNameAuth : Route
    @Serializable
    data object SelectYearOfBirth : Route
    data object OnBoarding : Route
    @Serializable
    data object GalleryUpload : Route
    @Serializable
    data object GalleryPostDetail : Route
    @Serializable
    data object ChatRoom : Route
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