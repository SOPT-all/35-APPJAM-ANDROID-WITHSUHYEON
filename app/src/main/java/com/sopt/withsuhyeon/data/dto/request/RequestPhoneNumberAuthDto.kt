package com.sopt.withsuhyeon.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPhoneNumberAuthDto(
    @SerialName("phoneNumber")
    val phoneNumber: String
)