package com.sopt.withsuhyeon.data.repositoryimpl

import com.sopt.withsuhyeon.data.dto.response.ChatRoom
import com.sopt.withsuhyeon.data.service.ChatService
import com.sopt.withsuhyeon.domain.entity.ChatMessage
import com.sopt.withsuhyeon.domain.repository.ChatRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatService: ChatService
) : ChatRepository {
    override suspend fun getTotalChatRooms() : Result<List<ChatRoom>> =
        runCatching {
            val response = chatService.getTotalChatRooms()
            response.result?.chatRooms ?: throw Exception("Response data is null")
        }

    override suspend fun getChatRoomMessageTotal(request: String): Result<List<ChatMessage>> =
        runCatching {
            val response = chatService.getChatRoomMessageTotal(request)
            response.result?.chatMessages ?: throw Exception("Response data is null")
        }

    override suspend fun patchEnterChatRoom(request: String): Result<Unit> =
        runCatching {
            val response = chatService.patchEnterChatRoom(request)
            response.result
        }

    override suspend fun patchExitChatRoom(request: String): Result<Unit> =
        runCatching {
            val response = chatService.patchExitChatRoom(request)
            response.result
        }
}