package com.sopt.withsuhyeon.data.service

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.request.RequestAuthNumberDto
import com.sopt.withsuhyeon.data.dto.request.RequestPhoneNumberAuthDto
import com.sopt.withsuhyeon.data.dto.response.ResponseRegionListDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SignUpService {
    @POST("/api/v1/message/send")
    suspend fun postPhoneNumberAuth(
        @Query("flow") flow: String,
        @Body request: RequestPhoneNumberAuthDto
    ): BaseResponse<Unit>

    @POST("/api/v1/message/verify")
    suspend fun verifyAuthNumber(
        @Query("flow") flow: String,
        @Body request: RequestAuthNumberDto
    ) : BaseResponse<Unit>

    @GET("/api/v1/enums/regions")
    suspend fun getRegionList(
    ) : BaseResponse<ResponseRegionListDto>
}