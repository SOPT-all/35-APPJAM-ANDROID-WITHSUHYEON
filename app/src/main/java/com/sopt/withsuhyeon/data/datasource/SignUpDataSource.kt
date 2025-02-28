package com.sopt.withsuhyeon.data.datasource

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseRegionListDto

interface SignUpDataSource {
    suspend fun postPhoneNumberAuth(phoneNumber: String): BaseResponse<Unit>
    suspend fun postVerifyNumber(phoneNumber: String, verifyNumber: String): BaseResponse<Unit>
    suspend fun getRegionList(): BaseResponse<ResponseRegionListDto>
    suspend fun postSignUp(
        phoneNumber: String,
        nickname: String,
        birthYear: Int,
        gender: Boolean,
        profileImage: String,
        region: String
    ): BaseResponse<Unit>
}