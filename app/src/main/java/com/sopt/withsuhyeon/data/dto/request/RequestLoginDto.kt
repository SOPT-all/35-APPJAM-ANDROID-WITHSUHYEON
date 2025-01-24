package com.sopt.withsuhyeon.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginDto(
    @SerialName("phoneNumber")
    val phoneNumber: String
)

@Serializable
data class RequestVerifyDto(
    @SerialName("phoneNumber")
    val phoneNumber: String,
    @SerialName("verifyNumber")
    val verifyNumber: String
)