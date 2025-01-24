package com.sopt.withsuhyeon.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class ChatRoomInfoModel(
    val postId: Int,
    val ownerId: Int,
    val writerId: Int,
    val ownerChatRoomId: String,
    val peerChatRoomId: String
)