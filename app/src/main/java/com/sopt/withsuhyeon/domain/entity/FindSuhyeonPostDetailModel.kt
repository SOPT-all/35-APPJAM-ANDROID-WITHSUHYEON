package com.sopt.withsuhyeon.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class FindSuhyeonPostDetailModel(
    val title: String,
    val content: String,
    val nickname: String,
    val createdAt: String,
    val profileImage: String,
    val price: Int,
    val owner: Boolean,
    val matching: Boolean,
    val isExpired: Boolean,
    val postDetailInfo: PostDetailInfoModel,
    val chatRoomInfoPost: ChatRoomInfoModel?
)