package com.sopt.withsuhyeon.domain.entity

import com.sopt.withsuhyeon.data.dto.response.ChatRoom
import kotlinx.serialization.Serializable

@Serializable
data class ChatRoomDetailModel(
    val ownerChatRoomId: String,
    val peerChatRoomId: String,
    val postId: Int,
    val chatOwnerId: Int,
    val chatPeerId: Int,
    val chatPeerNickname: String,
    val chatPeerProfileImage: String,
    val postTitle: String,
    val postPlace: String,
    val postCost: Int,
)

fun ChatRoom.toChatRoomDetailModel(): ChatRoomDetailModel {
    return ChatRoomDetailModel(
        ownerChatRoomId = this.ownerChatRoomId,
        peerChatRoomId = this.peerChatRoomId,
        postId = this.postId,
        chatOwnerId = this.chatOwnerId,
        chatPeerId = this.chatPeerId,
        chatPeerNickname = this.chatPeerNickname,
        chatPeerProfileImage = this.chatPeerProfileImage,
        postTitle = this.postTitle,
        postPlace = this.postPlace,
        postCost = this.postCost
    )
}