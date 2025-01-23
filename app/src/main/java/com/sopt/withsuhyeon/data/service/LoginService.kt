package com.sopt.withsuhyeon.data.service

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.request.RequestLoginDto
import com.sopt.withsuhyeon.data.dto.request.RequestVerifyDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/api/v1/message/send")
    suspend fun postLoginPhoneNumberAuth(
        @Body request: RequestLoginDto
    ): BaseResponse<Unit>

    @POST("/api/v1/message/verify?flow=signin")
    suspend fun postLoginVerify(
        @Body request: RequestVerifyDto
    ): BaseResponse<Unit>


    @POST("/api/v1/auth/signin")
    suspend fun postLogin(
        @Body request: RequestLoginDto
    ): BaseResponse<Unit>
}