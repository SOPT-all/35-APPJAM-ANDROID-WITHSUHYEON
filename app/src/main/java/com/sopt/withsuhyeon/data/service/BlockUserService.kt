package com.sopt.withsuhyeon.data.service

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.request.RequestBlockUserDto
import com.sopt.withsuhyeon.data.dto.response.ResponseBlockUserDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BlockUserService {
    @GET("/api/v1/mypage/blocks")
    suspend fun getBlockUser(): BaseResponse<ResponseBlockUserDto>

    @POST("/api/v1/mypage/blocks")
    suspend fun postBlockUser(
        @Body request: RequestBlockUserDto
    ): BaseResponse<Unit>

    @DELETE("/api/v1/mypage/blocks")
    suspend fun deleteBlockUser(
        @Query("number") number: String
    ): BaseResponse<Unit>
}