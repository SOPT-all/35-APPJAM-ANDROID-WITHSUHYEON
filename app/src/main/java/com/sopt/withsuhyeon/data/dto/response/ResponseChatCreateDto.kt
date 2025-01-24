package com.sopt.withsuhyeon.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseChatCreateDto(
    val type: String,
    val ownerChatRoomId: String,
    val peerChatRoomId: String
)
