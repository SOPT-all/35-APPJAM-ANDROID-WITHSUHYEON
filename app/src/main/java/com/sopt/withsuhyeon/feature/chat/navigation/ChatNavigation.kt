package com.sopt.withsuhyeon.feature.chat.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.withsuhyeon.core.navigation.MainTabRoute
import com.sopt.withsuhyeon.feature.chat.ChatRoute

fun NavController.navigateToChat(navOptions: NavOptions) {
    navigate(MainTabRoute.Chat, navOptions)
}

fun NavGraphBuilder.chatNavGraph(
    padding: PaddingValues
) {
    composable<MainTabRoute.Chat> {
        ChatRoute(padding)
    }
}