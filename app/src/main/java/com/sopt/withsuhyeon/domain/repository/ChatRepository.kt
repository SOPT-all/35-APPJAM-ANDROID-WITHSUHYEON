package com.sopt.withsuhyeon.domain.repository

import com.sopt.withsuhyeon.data.dto.response.ChatRoom
import com.sopt.withsuhyeon.domain.entity.ChatMessage

interface ChatRepository {
    suspend fun getTotalChatRooms(): Result<List<ChatRoom>>
    suspend fun getChatRoomMessageTotal(request: String): Result<List<ChatMessage>>
    suspend fun patchEnterChatRoom(request: String): Result<Unit>
    suspend fun patchExitChatRoom(request: String): Result<Unit>
}