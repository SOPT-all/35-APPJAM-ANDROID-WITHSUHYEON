package com.sopt.withsuhyeon.data.dto.response

import com.sopt.withsuhyeon.domain.entity.ChatMessage
import kotlinx.serialization.Serializable

@Serializable
data class ResponseChatRoomMessageTotalDto(
    val chatMessages: List<ChatMessage>
)
