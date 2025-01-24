package com.sopt.withsuhyeon.data.mapper

import com.sopt.withsuhyeon.data.dto.response.ResponseLoginDto
import com.sopt.withsuhyeon.domain.entity.LoginTokenModel

fun ResponseLoginDto.toLoginTokenModel(): LoginTokenModel {
    return LoginTokenModel(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken
    )
}