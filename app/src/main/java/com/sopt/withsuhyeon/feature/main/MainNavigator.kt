package com.sopt.withsuhyeon.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.sopt.withsuhyeon.core.navigation.MainTabRoute
import com.sopt.withsuhyeon.core.navigation.Route
import com.sopt.withsuhyeon.feature.chat.navigation.navigateToChat
import com.sopt.withsuhyeon.feature.chat.navigation.navigateToChatRoom
import com.sopt.withsuhyeon.feature.findsuhyeon.navigation.navigateToFindSuhyeon
import com.sopt.withsuhyeon.feature.findsuhyeon.navigation.navigateToFindSuhyeonPost
import com.sopt.withsuhyeon.feature.findsuhyeon.navigation.navigateToFindSuhyeonUpload
import com.sopt.withsuhyeon.feature.findsuhyeon.navigation.navigateToFindSuhyeonUploadDetail
import com.sopt.withsuhyeon.feature.gallery.navigation.navigateToGallery
import com.sopt.withsuhyeon.feature.gallery.navigation.navigateToGalleryPostDetail
import com.sopt.withsuhyeon.feature.gallery.navigation.navigateToGalleryUpload
import com.sopt.withsuhyeon.feature.home.navigation.navigateToBlockUser
import com.sopt.withsuhyeon.feature.home.navigation.navigateToHome
import com.sopt.withsuhyeon.feature.mypage.navigation.navigateToBlockUserFromMyPage
import com.sopt.withsuhyeon.feature.mypage.navigation.navigateToFavoriteLocation
import com.sopt.withsuhyeon.feature.mypage.navigation.navigateToMyPage
import com.sopt.withsuhyeon.feature.mypage.navigation.navigateToMyPagePost
import com.sopt.withsuhyeon.feature.mypage.navigation.navigateToOnBoarding
import com.sopt.withsuhyeon.feature.mypage.navigation.navigateToWithdraw
import com.sopt.withsuhyeon.feature.onboarding.navigation.navigateToLogin
import com.sopt.withsuhyeon.feature.onboarding.navigation.navigateToNickNameAuth
import com.sopt.withsuhyeon.feature.onboarding.navigation.navigateToOnboardingFinish
import com.sopt.withsuhyeon.feature.onboarding.navigation.navigateToPhoneNumberAuth
import com.sopt.withsuhyeon.feature.onboarding.navigation.navigateToPostProfileImage
import com.sopt.withsuhyeon.feature.onboarding.navigation.navigateToSelectGender
import com.sopt.withsuhyeon.feature.onboarding.navigation.navigateToSelectLocation
import com.sopt.withsuhyeon.feature.onboarding.navigation.navigateToSelectYearOfBirth
import com.sopt.withsuhyeon.feature.onboarding.navigation.navigateToSignUp


class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = MainTabRoute.Home

    val currentTab: MainTab?
        @Composable get() = MainTab.entries.find { tab ->
            when (tab.route) {
                is MainTabRoute.Gallery -> currentDestination?.route?.startsWith(MainTabRoute.Gallery::class.qualifiedName!!) == true
                else -> currentDestination?.route == tab.route::class.qualifiedName
            }
        }

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(MainTab.HOME.route) {
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

    fun navigateToLogin() {
        navController.navigateToLogin()
    }

    fun navigateToSignUp() {
        navController.navigateToSignUp()
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

    fun navigateToBlockUser() {
        navController.navigateToBlockUser()
    }
    fun navigateToMyPageFavoriteLocation() {
        navController.navigateToFavoriteLocation()
    }
    fun navigateToMyPageWithdraw() {
        navController.navigateToWithdraw()
    }
    fun navigateToMyPagePost() {
        navController.navigateToMyPagePost()
    }

    fun navigateToBlockUserFromMyPage() {
        navController.navigateToBlockUserFromMyPage()
    }

    fun navigateToOnboarding() {
        navController.navigateToOnBoarding()
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
    fun navigateToGallery(navOptions: NavOptions? = null, category: String? = null) {
        navController.navigateToGallery(
            navOptions ?: navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
                launchSingleTop = true
            },
            category
        )
    }
    fun navigateToGalleryUpload() {
        navController.navigateToGalleryUpload()
    }

    fun navigateToGalleryPostDetail(galleryId: Long) {
        navController.navigateToGalleryPostDetail(galleryId)
    }

    fun navigateToChatRoom() {
        navController.navigateToChatRoom()
    }

    fun navigateToFindSuhyeon(navOptions: NavOptions? = null) {
        navController.navigateToFindSuhyeon(
            navOptions ?: navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        )
    }

    fun navigateToFindSuhyeonUpload() {
        navController.navigateToFindSuhyeonUpload()
    }

    fun navigateToFindSuhyeonUploadDetail() {
        navController.navigateToFindSuhyeonUploadDetail()
    }

    fun navigateToFindSuhyeonPost(id: Long) {
        navController.navigateToFindSuhyeonPost(id)
    }

    fun popBackStack() {
        navController.popBackStack()
    }

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean =
        navController.currentDestination?.route == T::class.qualifiedName

    @Composable
    fun shouldShowBottomBar(): Boolean {
        return currentDestination?.route?.let { currentRoute ->
            MainTab.entries.any { tab ->
                when (tab.route) {
                    is MainTabRoute.Home -> currentRoute.startsWith(MainTabRoute.Home::class.qualifiedName!!)
                    is MainTabRoute.FindSuhyeon -> currentRoute.startsWith(MainTabRoute.FindSuhyeon::class.qualifiedName!!)
                    is MainTabRoute.Gallery -> currentRoute.startsWith(MainTabRoute.Gallery::class.qualifiedName!!)
                    is MainTabRoute.Chat -> currentRoute.startsWith(MainTabRoute.Chat::class.qualifiedName!!)
                    is MainTabRoute.MyPage -> currentRoute.startsWith(MainTabRoute.MyPage::class.qualifiedName!!)
                }
            }
        } ?: false
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}