package com.sopt.withsuhyeon.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class ChatRoomInfoModel(
    val postId: Long,
    val ownerId: Long,
    val writerId: Long,
    val ownerChatRoomId: String,
    val peerChatRoomId: String
)