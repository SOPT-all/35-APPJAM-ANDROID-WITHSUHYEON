package com.sopt.withsuhyeon.data.service

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseChatRoomMessageTotalDto
import com.sopt.withsuhyeon.data.dto.response.ResponseChatRoomsDto
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ChatService {
    @GET("/api/v1/chatrooms")
    suspend fun getTotalChatRooms() : BaseResponse<ResponseChatRoomsDto>

    @GET("/api/v1/chatrooms/{chatRoomId}")
    suspend fun getChatRoomMessageTotal(
        @Path("chatRoomId") chatRoomId: String
    ) : BaseResponse<ResponseChatRoomMessageTotalDto>

    @PATCH("/api/v1/chatrooms/join/{chatRoomId}")
    suspend fun patchEnterChatRoom(
        @Path("chatRoomId") chatRoomId: String
    ) : BaseResponse<Unit>

    @PATCH("/api/v1/chatrooms/exit/{chatRoomId}")
    suspend fun patchExitChatRoom(
        @Path("chatRoomId") chatRoomId: String
    ) : BaseResponse<Unit>
}