package com.sopt.withsuhyeon.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class SocketResponse(
    val type: String,
    val ownerChatRoomId: String,
    val peerChatRoomId: String
)