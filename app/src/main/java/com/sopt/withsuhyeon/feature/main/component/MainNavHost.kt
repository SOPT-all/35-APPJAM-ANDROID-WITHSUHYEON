package com.sopt.withsuhyeon.feature.main.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import com.sopt.withsuhyeon.core.navigation.Route
import com.sopt.withsuhyeon.feature.chat.navigation.chatNavGraph
import com.sopt.withsuhyeon.feature.findsuhyeon.navigation.findSuhyeonNavGraph
import com.sopt.withsuhyeon.feature.findsuhyeon.viewmodel.FindSuhyeonUploadViewModel
import com.sopt.withsuhyeon.feature.gallery.navigation.galleryNavGraph
import com.sopt.withsuhyeon.feature.home.navigation.homeNavGraph
import com.sopt.withsuhyeon.feature.main.MainNavigator
import com.sopt.withsuhyeon.feature.mypage.navigation.myPageNavGraph
import com.sopt.withsuhyeon.feature.onboarding.navigation.onBoardingNavGraph
import com.sopt.withsuhyeon.feature.onboarding.viewmodel.LoginViewModel
import com.sopt.withsuhyeon.feature.onboarding.viewmodel.SignUpViewModel
import com.sopt.withsuhyeon.feature.splash.splashNavGraph
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.White)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
        ) {
            homeNavGraph(
                onNavigateToBlockUser = navigator::navigateToBlockUser,
                popBackStack = navigator::popBackStack,
                padding = PaddingValues(
                    start = padding.calculateStartPadding(layoutDirection = LayoutDirection.Ltr),
                    end = padding.calculateEndPadding(layoutDirection = LayoutDirection.Ltr),
                    bottom = padding.calculateBottomPadding(),
                    top = 0.dp
                ),
                navigateToGallery = { navigator.navigateToGallery() },
                navigateToGalleryWithCategory = { navigator.navigateToGallery(category = it) },
                navigateToPost = { navigator.navigateToFindSuhyeonPost(it) }
            )
            findSuhyeonNavGraph(
                padding = padding,
                onNavigateToFindSuheyonUpload = navigator::navigateToFindSuhyeonUpload,
                onNavigateToFindSuhyeon = { navigator.navigateToFindSuhyeon() },
                onNavigateToFindSuheyonUploadDetail = navigator::navigateToFindSuhyeonUploadDetail,
                onNavigateToFindSuhyeonPost = { navigator.navigateToFindSuhyeonPost(it) },
                getBackStackUploadViewModel = { navBackStackEntry ->
                    navigator.navController.previousBackStackEntry?.let { previousEntry ->
                        hiltViewModel<FindSuhyeonUploadViewModel>(previousEntry)
                    } ?: hiltViewModel(navBackStackEntry)
                }
            )

            galleryNavGraph(
                padding = padding,
                onNavigateToGalleryUpload = navigator::navigateToGalleryUpload,
                onNavigateToGalleryPostDetail = { navigator.navigateToGalleryPostDetail(it) },
                onPopBackStackToGallery = navigator::popBackStack
            )
            chatNavGraph(
                padding = padding,
                onNavigateToChatRoom = navigator::navigateToChatRoom
            )
            myPageNavGraph(
                padding = padding,
                onNavigateToBlockUser = navigator::navigateToBlockUserFromMyPage,
                onNavigateToOnboarding = navigator::navigateToOnboarding,
            )

            splashNavGraph(
                padding = padding,
                onNavigateToOnboarding = navigator::navigateToOnboarding
            )

            onBoardingNavGraph(
                padding = padding,
                onBoardingPadding = PaddingValues(
                    start = padding.calculateStartPadding(layoutDirection = LayoutDirection.Ltr),
                    end = padding.calculateEndPadding(layoutDirection = LayoutDirection.Ltr),
                    bottom = padding.calculateBottomPadding(),
                    top = 0.dp
                ),
                onNavigateToLogin = navigator::navigateToLogin,
                onNavigateToSignUp = navigator::navigateToSignUp,
                onNavigateToPhoneNumberAuth = navigator::navigateToPhoneNumberAuth,
                onNavigateToNickNameAuth = navigator::navigateToNicknameAuth,
                onNavigateToSelectYearOfBirth = navigator::navigateToSelectYearOfBirth,
                onNavigateToSelectGender = navigator::navigateToSelectGender,
                onNavigateToPostProfileImage = navigator::navigateToPostProfileImage,
                onNavigateToSelectLocation = navigator::navigateToSelectLocation,
                onNavigateToSignUpFinish = navigator::navigateToOnboardingFinish,
                onNavigateToLoginFinish = navigator::navigateToLoginFinish,
                onNavigateToHome = navigator::navigateToHome,
                getBackStackUploadViewModel = { navBackStackEntry ->
                    val parentEntry = try {
                        navigator.navController.getBackStackEntry(Route.OnBoarding)
                    } catch (e: IllegalArgumentException) {
                        null
                    }
                    parentEntry?.let { hiltViewModel<SignUpViewModel>(it) } ?: hiltViewModel(
                        navBackStackEntry
                    )
                },
                getBackStackLoginViewModel = { navBackStackEntry ->
                    navigator.navController.previousBackStackEntry?.let { previousEntry ->
                        hiltViewModel<LoginViewModel>(previousEntry)
                    } ?: hiltViewModel(navBackStackEntry)
                }
            )
        }
    }
}