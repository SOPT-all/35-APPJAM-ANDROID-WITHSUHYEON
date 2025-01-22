package com.sopt.withsuhyeon.data.service

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseHomeDto
import retrofit2.http.GET

interface HomeService {
    @GET("/api/v1/home")
    suspend fun getHomeData() : BaseResponse<ResponseHomeDto>
}