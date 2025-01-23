package com.sopt.withsuhyeon.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class ChatRoomsModel(
    val ownerChatRoomId: String,
    val peerChatRoomId: String,
    val postId: Int,
    val chatOwnerId: Int,
    val chatPeerId: Int,
    val chatPeerNickname: String,
    val chatPeerProfileImage: String,
    val lastChatMessage: String,
    val lastChatAt: String,
    val unReadCount: Int
)