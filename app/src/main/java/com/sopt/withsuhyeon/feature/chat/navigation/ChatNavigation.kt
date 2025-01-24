package com.sopt.withsuhyeon.feature.chat.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.sopt.withsuhyeon.core.navigation.MainTabRoute
import com.sopt.withsuhyeon.core.navigation.Route
import com.sopt.withsuhyeon.data.dto.response.ChatRoom
import com.sopt.withsuhyeon.domain.entity.ChatRoomDetailModel
import com.sopt.withsuhyeon.domain.entity.ChatRoomInfoModel
import com.sopt.withsuhyeon.feature.chat.ChatRoomRealRoute
import com.sopt.withsuhyeon.feature.chat.ChatRoomRoute
import com.sopt.withsuhyeon.feature.chat.ChatRoute

fun NavController.navigateToChat(navOptions: NavOptions) {
    navigate(MainTabRoute.Chat, navOptions)
}

fun NavController.navigateToChatRoom(postId: Long?, ownerId: Long?, writerId: Long?, ownerChatRoomId: String?, peerChatRoomId: String?) {
    navigate(Route.ChatRoom(postId, ownerId, writerId, ownerChatRoomId, peerChatRoomId))
}

fun NavController.navigateToChatRoomReal(
    ownerChatRoomId: String?,
    peerChatRoomId: String?,
    postId: Int?,
    chatOwnerId: Int?,
    chatPeerId: Int?,
    chatPeerNickname: String?,
    chatPeerProfileImage: String?,
    postTitle: String?,
    postPlace: String?,
    postCost: Int?
) {
    navigate(Route.ChatRoomReal(ownerChatRoomId, peerChatRoomId, postId, chatOwnerId, chatPeerId, chatPeerNickname, chatPeerProfileImage, postTitle, postPlace, postCost))
}

fun NavGraphBuilder.chatNavGraph(
    padding: PaddingValues,
    onNavigateToChatRoomReal: (ChatRoom) -> Unit
) {
    composable<MainTabRoute.Chat> {
        ChatRoute(
            padding = padding,
            navigateToChatRoomReal = onNavigateToChatRoomReal
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

    composable<Route.ChatRoomReal> { navBackStackEntry ->
        val ownerChatRoomId = navBackStackEntry.toRoute<Route.ChatRoomReal>().ownerChatRoomId
        val peerChatRoomId = navBackStackEntry.toRoute<Route.ChatRoomReal>().peerChatRoomId
        val postId = navBackStackEntry.toRoute<Route.ChatRoomReal>().postId
        val chatOwnerId = navBackStackEntry.toRoute<Route.ChatRoomReal>().chatOwnerId
        val chatPeerId = navBackStackEntry.toRoute<Route.ChatRoomReal>().chatPeerId
        val chatPeerNickname = navBackStackEntry.toRoute<Route.ChatRoomReal>().chatPeerNickname
        val chatPeerProfileImage = navBackStackEntry.toRoute<Route.ChatRoomReal>().chatPeerProfileImage
        val postTitle = navBackStackEntry.toRoute<Route.ChatRoomReal>().postTitle
        val postPlace = navBackStackEntry.toRoute<Route.ChatRoomReal>().postPlace
        val postCost = navBackStackEntry.toRoute<Route.ChatRoomReal>().postCost

        val chatRoomDetailModel = ChatRoomDetailModel(
            ownerChatRoomId = ownerChatRoomId.orEmpty(),
            peerChatRoomId = peerChatRoomId.orEmpty(),
            postId = postId ?: 0,
            chatOwnerId = chatOwnerId ?: 0,
            chatPeerId = chatPeerId ?: 0,
            chatPeerNickname = chatPeerNickname.orEmpty(),
            chatPeerProfileImage = chatPeerProfileImage.orEmpty(),
            postTitle = postTitle.orEmpty(),
            postPlace = postPlace.orEmpty(),
            postCost = postCost ?: 0
        )

        ChatRoomRealRoute(
            padding = padding,
            chatRoomDetailModel = chatRoomDetailModel
        )
    }
}