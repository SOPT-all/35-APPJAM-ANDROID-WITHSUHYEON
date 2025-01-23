package com.sopt.withsuhyeon.domain.entity

data class ChatRoomListInfoModel(
    val profileImage: String,
    val partnerName: String,
    val recentChat: String,
    val recentChatDateTime: String,
    val unreadChatCount: String
)