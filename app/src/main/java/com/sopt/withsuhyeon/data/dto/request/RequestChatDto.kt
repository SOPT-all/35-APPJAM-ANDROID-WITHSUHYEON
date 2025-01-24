package com.sopt.withsuhyeon.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestChatDto(
    @SerialName("ownerChatRoomId")
    val ownerChatRoomId: String,
    @SerialName("peerChatRoomId")
    val peerChatRoomId: String,
    @SerialName("senderId")
    val senderId: Int,
    @SerialName("receiverId")
    val receiverId: Int,
    @SerialName("postId")
    val postId: Int,
    @SerialName("content")
    val content: String,
    @SerialName("type")
    val type: String,
)
