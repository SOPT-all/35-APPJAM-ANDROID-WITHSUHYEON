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
import com.sopt.withsuhyeon.core.navigation.MainTabRoute
import com.sopt.withsuhyeon.core.navigation.Route
import com.sopt.withsuhyeon.feature.chat.navigation.navigateChat
import com.sopt.withsuhyeon.feature.findsuhyeon.navigation.navigateFindSuhyeon
import com.sopt.withsuhyeon.feature.gallery.navigation.navigateGallery
import com.sopt.withsuhyeon.feature.home.navigation.navigateHome
import com.sopt.withsuhyeon.feature.mypage.navigation.navigateMyPage
import com.sopt.withsuhyeon.feature.onboarding.navigation.navigateOnBoarding

class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = Route.OnBoarding

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
            MainTab.HOME -> navController.navigateHome(navOptions)
            MainTab.FINDSUHYEON -> navController.navigateFindSuhyeon(navOptions)
            MainTab.GALLERY -> navController.navigateGallery(navOptions)
            MainTab.CHAT -> navController.navigateChat(navOptions)
            MainTab.MYPAGE -> navController.navigateMyPage(navOptions)
        }
    }

    fun navigateOnBoarding() {
        navController.navigateOnBoarding()
    }

    fun navigateHome(navOptions: NavOptions? = null) {
        navController.navigateHome(
            navOptions ?: navOptions {
                popUpTo(MainTab.HOME.route) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        )
    }


    private fun popBackStack() {
        navController.popBackStack()
    }

    fun popBackStackIfNotHome() {
        if (!isSameCurrentDestination<MainTabRoute.Home>()) {
            popBackStack()
        }
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