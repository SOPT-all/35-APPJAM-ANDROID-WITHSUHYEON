package com.sopt.withsuhyeon.feature.chat.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.withsuhyeon.core.navigation.MainTabRoute
import com.sopt.withsuhyeon.core.navigation.Route
import com.sopt.withsuhyeon.feature.chat.ChatRoomRoute
import com.sopt.withsuhyeon.feature.chat.ChatRoute

fun NavController.navigateToChat(navOptions: NavOptions) {
    navigate(MainTabRoute.Chat, navOptions)
}

fun NavController.navigateToChatRoom() {
    navigate(Route.ChatRoom)
}

fun NavGraphBuilder.chatNavGraph(
    padding: PaddingValues,
    onNavigateToChatRoom: () -> Unit
) {
    composable<MainTabRoute.Chat> {
        ChatRoute(
            padding = padding,
            navigateToChatRoom = onNavigateToChatRoom
        )
    }
    composable<Route.ChatRoom> {
        ChatRoomRoute(padding)
    }
}