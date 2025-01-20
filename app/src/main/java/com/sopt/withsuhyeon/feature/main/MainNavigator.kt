package com.sopt.withsuhyeon.feature.main

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.sopt.withsuhyeon.core.navigation.Route
import com.sopt.withsuhyeon.feature.chat.navigation.navigateToChat
import com.sopt.withsuhyeon.feature.findsuhyeon.navigation.navigateToFindSuhyeon
import com.sopt.withsuhyeon.feature.gallery.navigation.navigateToGallery
import com.sopt.withsuhyeon.feature.gallery.navigation.navigateToGalleryPostDetail
import com.sopt.withsuhyeon.feature.gallery.navigation.navigateToGalleryUpload
import com.sopt.withsuhyeon.feature.home.navigation.navigateToHome
import com.sopt.withsuhyeon.feature.mypage.navigation.navigateToMyPage
import com.sopt.withsuhyeon.feature.onboarding.navigation.navigateToNickNameAuth
import com.sopt.withsuhyeon.feature.onboarding.navigation.navigateToOnboardingFinish
import com.sopt.withsuhyeon.feature.onboarding.navigation.navigateToPhoneNumberAuth
import com.sopt.withsuhyeon.feature.onboarding.navigation.navigateToPostProfileImage
import com.sopt.withsuhyeon.feature.onboarding.navigation.navigateToSelectGender
import com.sopt.withsuhyeon.feature.onboarding.navigation.navigateToSelectLocation
import com.sopt.withsuhyeon.feature.onboarding.navigation.navigateToSelectYearOfBirth


class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = Route.SelectLocation

    val currentTab: MainTab?
        @SuppressLint("RestrictedApi") @Composable get() = MainTab.find { tab ->
            currentDestination?.route == tab::class.qualifiedName
        }

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(MainTab.HOME.route)  {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.HOME -> navController.navigateToHome(navOptions)
            MainTab.FINDSUHYEON -> navController.navigateToFindSuhyeon(navOptions)
            MainTab.GALLERY -> navController.navigateToGallery(navOptions)
            MainTab.CHAT -> navController.navigateToChat(navOptions)
            MainTab.MYPAGE -> navController.navigateToMyPage(navOptions)
        }
    }
    fun navigateToPhoneNumberAuth() {
        navController.navigateToPhoneNumberAuth()
    }

    fun navigateToNicknameAuth() {
        navController.navigateToNickNameAuth()
    }

    fun navigateToSelectYearOfBirth() {
        navController.navigateToSelectYearOfBirth()
    }
    fun navigateToSelectGender() {
        navController.navigateToSelectGender()
    }
    fun navigateToPostProfileImage() {
        navController.navigateToPostProfileImage()
    }
    fun navigateToSelectLocation() {
        navController.navigateToSelectLocation()
    }
    fun navigateToOnboardingFinish() {
        navController.navigateToOnboardingFinish()
    }

    fun navigateToHome(navOptions: NavOptions? = null) {
        navController.navigateToHome(
            navOptions ?: navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        )
    }

    fun navigateToGalleryUpload() {
        navController.navigateToGalleryUpload()
    }

    fun navigateToGalleryPostDetail() {
        navController.navigateToGalleryPostDetail()
    }

    fun popBackStack() {
        navController.popBackStack()
    }

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean =
        navController.currentDestination?.route == T::class.qualifiedName

    @Composable
    fun shouldShowBottomBar() = MainTab.contains {
        currentDestination?.route == it::class.qualifiedName
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}