package com.sopt.withsuhyeon.data.datasourceimpl

import com.sopt.withsuhyeon.data.datasource.LoginDataSource
import com.sopt.withsuhyeon.data.dto.request.RequestLoginDto
import com.sopt.withsuhyeon.data.dto.request.RequestVerifyDto
import com.sopt.withsuhyeon.data.service.LoginService
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(
    private val loginService: LoginService
) : LoginDataSource {
    override suspend fun postLogin(phoneNumber: String) {
        loginService.postLogin(
            request = RequestLoginDto(phoneNumber)
        )
    }

    override suspend fun postLoginPhoneNumberAuth(phoneNumber: String) {
        loginService.postLoginPhoneNumberAuth(
            request = RequestLoginDto(phoneNumber)
        )
    }

    override suspend fun postLoginVerify(phoneNumber: String, verifyNumber: String) {
        loginService.postLoginVerify(
            request = RequestVerifyDto(
                phoneNumber,
                verifyNumber
            )
        )
    }
}