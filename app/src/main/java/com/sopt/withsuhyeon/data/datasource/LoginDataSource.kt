package com.sopt.withsuhyeon.data.datasource

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseLoginDto

interface LoginDataSource {
    suspend fun postLoginPhoneNumberAuth(phoneNumber: String)
    suspend fun postLoginVerify(phoneNumber: String, verifyNumber: String)
    suspend fun postLogin(phoneNumber: String) : BaseResponse<ResponseLoginDto>
}