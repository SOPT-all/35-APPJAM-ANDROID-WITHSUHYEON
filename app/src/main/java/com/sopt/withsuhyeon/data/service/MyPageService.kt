package com.sopt.withsuhyeon.data.service

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseMyPageInfoDto
import retrofit2.http.GET

interface MyPageService {
    @GET("/api/v1/mypage")
    suspend fun getMyPageInfo() : BaseResponse<ResponseMyPageInfoDto>
}