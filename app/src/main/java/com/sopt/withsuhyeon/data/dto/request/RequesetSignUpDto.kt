package com.sopt.withsuhyeon.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUpDto(
    @SerialName("phoneNumber")
    val phoneNumber: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("birthYear")
    val birthYear: Int,
    @SerialName("gender")
    val gender: Boolean,
    @SerialName("profileImage")
    val profileImage: String,
    @SerialName("region")
    val region: String
)