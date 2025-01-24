package com.sopt.withsuhyeon.data.mapper

import com.sopt.withsuhyeon.data.dto.response.ResponseMyPageInfoDto
import com.sopt.withsuhyeon.domain.entity.MyPageInfo

fun ResponseMyPageInfoDto.toMyPageInfoModel(): MyPageInfo {
    return MyPageInfo(
        nickname = this.nickname,
        profileImage = this.profileImage
    )
}