package com.sopt.withsuhyeon.feature.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.navigation.MainTabRoute
import com.sopt.withsuhyeon.core.navigation.Route

enum class MainTab(
    @DrawableRes val defaultIconResId: Int,
    @DrawableRes val selectIconResId: Int,
    @StringRes val descriptionResId: Int,
    val route: MainTabRoute,
) {
    HOME(
        defaultIconResId = R.drawable.ic_home_default,
        selectIconResId =  R.drawable.ic_home_select,
        descriptionResId = R.string.bottom_navigation_bar_item_home,
        route = MainTabRoute.Home,
    ),
    FINDSUHYEON(
        defaultIconResId = R.drawable.ic_find_suhyeon_default,
        selectIconResId =  R.drawable.ic_find_suhyeon_select,
        descriptionResId = R.string.bottom_navigation_bar_item_find_suhyeon,
        MainTabRoute.FindSuhyeon
    ),
    GALLERY(
        defaultIconResId = R.drawable.ic_gallery_default,
        selectIconResId =  R.drawable.ic_gallery_select,
        descriptionResId = R.string.bottom_navigation_bar_item_gallery,
        MainTabRoute.Gallery(null),
    ),
    CHAT(
        defaultIconResId = R.drawable.ic_chat_default,
        selectIconResId =  R.drawable.ic_chat_select,
        descriptionResId = R.string.bottom_navigation_bar_item_chat,
        MainTabRoute.Chat,
    ),
    MYPAGE(
        defaultIconResId = R.drawable.ic_my_default,
        selectIconResId =  R.drawable.ic_my_select,
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