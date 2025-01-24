package com.sopt.withsuhyeon.data.mapper

import com.sopt.withsuhyeon.data.dto.response.ResponseBlockUserDto
import com.sopt.withsuhyeon.data.dto.response.ResponseLoginDto
import com.sopt.withsuhyeon.domain.entity.BlockUserModel
import com.sopt.withsuhyeon.domain.entity.LoginTokenModel

fun ResponseBlockUserDto.toBlockUserModel(): BlockUserModel {
    return BlockUserModel(
        nickname = this.ninkname,
        phoneNumbers = this.phoneNumbers
    )
}