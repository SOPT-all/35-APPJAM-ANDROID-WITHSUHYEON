package com.sopt.withsuhyeon.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object OnBoarding : Route
    @Serializable
    data object TermsOfUse : Route
    @Serializable
    data object PhoneNumberAuth : Route
    @Serializable
    data object NickNameAuth : Route
    @Serializable
    data object SelectYearOfBirth : Route
    @Serializable
    data object GalleryUpload : Route
    @Serializable
    data object GalleryPostDetail : Route
    @Serializable
    data object SelectGender : Route
    @Serializable
    data object PostProfileImage : Route
    @Serializable
    data object SelectLocation : Route
    @Serializable
    data object OnboardingFinish : Route
    @Serializable
    data object BlockUser: Route
    @Serializable
    data object Login: Route
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