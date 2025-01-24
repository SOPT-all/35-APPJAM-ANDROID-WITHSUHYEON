package com.sopt.withsuhyeon.data.service

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseUserIdDto
import retrofit2.http.GET

interface UserService {
    @GET("/api/v1/user-id")
    suspend fun getUserId() : BaseResponse<ResponseUserIdDto>
}