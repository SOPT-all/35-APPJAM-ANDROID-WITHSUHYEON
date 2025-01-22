package com.sopt.withsuhyeon.data.datasourceimpl

import com.sopt.withsuhyeon.data.datasource.SignUpDataSource
import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.request.RequestPhoneNumberAuthDto
import com.sopt.withsuhyeon.data.service.SignUpService
import javax.inject.Inject

class SignUpDataSourceImpl @Inject constructor(
    private val signUpService: SignUpService
) : SignUpDataSource {
    override suspend fun postPhoneNumberAuth(phoneNumber: String): BaseResponse<Unit> =
        signUpService.postPhoneNumberAuth(
            flow = "signup",
            request = RequestPhoneNumberAuthDto(phoneNumber = phoneNumber)
        )
}