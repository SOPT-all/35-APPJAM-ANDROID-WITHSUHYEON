package com.sopt.withsuhyeon.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(
    val type: String,
    val content: String,
    val timestamp: String,
    val isRead: Boolean
)
