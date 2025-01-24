package com.sopt.withsuhyeon.data.dto.response

import com.sopt.withsuhyeon.domain.entity.ChatRoomInfoModel
import com.sopt.withsuhyeon.domain.entity.PostDetailInfoModel
import kotlinx.serialization.Serializable

@Serializable
data class ResponseFindSuhyeonPostDetailDto(
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
    val chatRoomInfoPost: ChatRoomInfoModel
)
