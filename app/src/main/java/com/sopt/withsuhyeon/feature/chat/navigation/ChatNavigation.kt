package com.sopt.withsuhyeon.feature.chat.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.sopt.withsuhyeon.core.navigation.MainTabRoute
import com.sopt.withsuhyeon.core.navigation.Route
import com.sopt.withsuhyeon.domain.entity.ChatRoomInfoModel
import com.sopt.withsuhyeon.feature.chat.ChatRoomRoute
import com.sopt.withsuhyeon.feature.chat.ChatRoute

fun NavController.navigateToChat(navOptions: NavOptions) {
    navigate(MainTabRoute.Chat, navOptions)
}

fun NavController.navigateToChatRoom(postId: Long?, ownerId: Long?, writerId: Long?, ownerChatRoomId: String?, peerChatRoomId: String?) {
    navigate(Route.ChatRoom(postId, ownerId, writerId, ownerChatRoomId, peerChatRoomId))
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
    composable<Route.ChatRoom> { navBackStackEntry ->
        val postId = navBackStackEntry.toRoute<Route.ChatRoom>().postId
        val ownerId = navBackStackEntry.toRoute<Route.ChatRoom>().ownerId
        val writerId = navBackStackEntry.toRoute<Route.ChatRoom>().writerId
        val ownerChatRoomId = navBackStackEntry.toRoute<Route.ChatRoom>().ownerChatRoomId
        val peerChatRoomId = navBackStackEntry.toRoute<Route.ChatRoom>().peerChatRoomId

        val chatRoomInfoModel = ChatRoomInfoModel(
            postId = postId ?: 0,
            ownerId = ownerId ?: 0,
            writerId = writerId ?: 0,
            ownerChatRoomId = ownerChatRoomId.orEmpty(),
            peerChatRoomId = peerChatRoomId.orEmpty()
        )

        ChatRoomRoute(
            padding = padding,
            chatRoomInfoModel = chatRoomInfoModel
        )
    }
}