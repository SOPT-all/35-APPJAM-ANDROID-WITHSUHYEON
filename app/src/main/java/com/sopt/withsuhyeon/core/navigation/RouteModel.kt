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
    data class GalleryPostDetail(val galleryId: Long) : Route
    @Serializable
    data object SelectGender : Route
    @Serializable
    data object FindSuhyeonUpload : Route
    @Serializable
    data object FindSuhyeonUploadDetail : Route
    @Serializable
    data class FindSuhyeonPost(val id: Long) : Route
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
    @Serializable
    data object ChatRoom : Route
    @Serializable
    data object MyPageFavoriteLocation : Route
    @Serializable
    data object MyPagePost : Route
    @Serializable
    data object MyPageWithdraw : Route
    @Serializable
    data object LoginFinish: Route
}

sealed interface MainTabRoute : Route {
    @Serializable
    data object Home : MainTabRoute
    @Serializable
    data object FindSuhyeon : MainTabRoute
    @Serializable
    data class Gallery(val category: String?) : MainTabRoute
    @Serializable
    data object Chat : MainTabRoute
    @Serializable
    data object MyPage : MainTabRoute
}