package com.sopt.withsuhyeon.feature.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.navigation.MainTabRoute
import com.sopt.withsuhyeon.core.navigation.Route

enum class MainTab(
    @DrawableRes val iconResId: Int,
    @StringRes val descriptionResId: Int,
    val route: MainTabRoute,
) {
    HOME(
        iconResId = R.drawable.ic_home,
        descriptionResId = R.string.bottom_navigation_bar_item_home,
        route = MainTabRoute.Home,
    ),
    FINDSUHYEON(
        iconResId = R.drawable.ic_find,
        descriptionResId = R.string.bottom_navigation_bar_item_find_suhyeon,
        MainTabRoute.FindSuhyeon
    ),
    GALLERY(
        iconResId = R.drawable.ic_gallery,
        descriptionResId = R.string.bottom_navigation_bar_item_gallery,
        MainTabRoute.Gallery(null),
    ),
    CHAT(
        iconResId = R.drawable.ic_message,
        descriptionResId = R.string.bottom_navigation_bar_item_chat,
        MainTabRoute.Chat,
    ),
    MYPAGE(
        iconResId = R.drawable.ic_mypage,
        descriptionResId = R.string.bottom_navigation_bar_item_my,
        MainTabRoute.MyPage,
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}