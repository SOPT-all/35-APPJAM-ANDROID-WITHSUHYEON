package com.sopt.withsuhyeon.data.mapper

import com.sopt.withsuhyeon.data.dto.response.ResponseFindSuhyeonPostDetailDto
import com.sopt.withsuhyeon.domain.entity.FindSuhyeonPostDetailModel
fun ResponseFindSuhyeonPostDetailDto.toFindSuhyeonPostDetailModel(): FindSuhyeonPostDetailModel {
    return FindSuhyeonPostDetailModel(
        title = this.title,
        content = this.content,
        nickname = this.nickname,
        createdAt = this.createdAt,
        profileImage = this.profileImage,
        price = this.price,
        owner = this.owner,
        matching = this.matching,
        isExpired = this.isExpired,
        postDetailInfo = this.postDetailInfo,
        chatRoomInfoPost = this.chatRoomInfoPost
    )
}