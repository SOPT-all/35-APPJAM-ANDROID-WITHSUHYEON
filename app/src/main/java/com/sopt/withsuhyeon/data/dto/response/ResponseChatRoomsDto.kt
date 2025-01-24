package com.sopt.withsuhyeon.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseChatRoomsDto(
    val chatRooms: List<ChatRoom>
)

@Serializable
data class ChatRoom(
    val ownerChatRoomId: String,
    val peerChatRoomId: String,
    val postId: Int,
    val chatOwnerId: Int,
    val chatPeerId: Int,
    val chatPeerNickname: String,
    val lastChatMessage: String,
    val lastChatAt: String,
    val unReadCount: Int
)