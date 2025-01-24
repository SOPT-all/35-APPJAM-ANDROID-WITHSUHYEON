package com.sopt.withsuhyeon.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMyPageInfoDto(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("profileImage")
    val profileImage: String
)
