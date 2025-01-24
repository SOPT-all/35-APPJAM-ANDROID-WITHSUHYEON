package com.sopt.withsuhyeon.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseBlockUserDto (
    @SerialName("nickname")
    val ninkname: String,
    @SerialName("phoneNumbers")
    val phoneNumbers: List<String>
)

