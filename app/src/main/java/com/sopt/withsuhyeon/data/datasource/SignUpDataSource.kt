package com.sopt.withsuhyeon.data.datasource

import com.sopt.withsuhyeon.data.dto.base.BaseResponse

interface SignUpDataSource {
    suspend fun postPhoneNumberAuth(phoneNumber: String): BaseResponse<Unit>
    suspend fun postVerifyNumber(phoneNumber: String, verifyNumber: String): BaseResponse<Unit>
}