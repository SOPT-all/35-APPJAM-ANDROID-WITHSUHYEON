package com.sopt.withsuhyeon.data.dto.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("errorCode")
    val errorCode: String? = null,
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: T? = null,
)