package com.sopt.withsuhyeon.feature.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.sopt.withsuhyeon.feature.chat.navigation.chatNavGraph
import com.sopt.withsuhyeon.feature.findsuhyeon.navigation.findSuhyeonNavGraph
import com.sopt.withsuhyeon.feature.gallery.navigation.galleryNavGraph
import com.sopt.withsuhyeon.feature.home.navigation.homeNavGraph
import com.sopt.withsuhyeon.feature.main.MainNavigator
import com.sopt.withsuhyeon.feature.mypage.navigation.myPageNavGraph
import com.sopt.withsuhyeon.feature.onboarding.navigation.onBoardingNavGraph
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.Grey50)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
        ) {
            homeNavGraph(
                padding = padding,
                onNavigateToBlockUser = navigator::navigateToBlockUser,
                onNavigateToHome = navigator::navigateToHome,
            )
            findSuhyeonNavGraph(
                padding = padding,
            )

            galleryNavGraph(
                padding = padding,
                onNavigateToGalleryUpload = navigator::navigateToGalleryUpload,
                onNavigateToGalleryPostDetail = navigator::navigateToGalleryPostDetail,
                onPopBackStackToGallery = navigator::popBackStack
            )
            chatNavGraph(
                padding = padding,
            )
            myPageNavGraph(
                padding = padding,
                onNavigateToBlockUser = navigator::navigateToBlockUserFromMyPage
            )
            onBoardingNavGraph(
                padding = padding,
                onNavigateToLogin =  navigator::navigateToLogin,
                onNavigateToSignUp = navigator::navigateToSignUp,
                onNavigateToPhoneNumberAuth = navigator::navigateToPhoneNumberAuth,
                onNavigateToNickNameAuth = navigator::navigateToNicknameAuth,
                onNavigateToSelectYearOfBirth = navigator::navigateToSelectYearOfBirth,
                onNavigateToSelectGender = navigator::navigateToSelectGender,
                onNavigateToPostProfileImage = navigator::navigateToPostProfileImage,
                onNavigateToSelectLocation = navigator::navigateToSelectLocation,
                onNavigateToFinish = navigator::navigateToOnboardingFinish,
                onNavigateToHome = navigator::navigateToHome,
            )
        }
    }
}