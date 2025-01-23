package com.sopt.withsuhyeon.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseChatDto(
    val content: String,
    val timestamp: String
)
