package com.sopt.withsuhyeon.data.repositoryimpl

import com.sopt.withsuhyeon.data.dto.request.RequestLoginDto
import com.sopt.withsuhyeon.data.dto.request.RequestVerifyDto
import com.sopt.withsuhyeon.data.mapper.toLoginTokenModel
import com.sopt.withsuhyeon.data.service.LoginService
import com.sopt.withsuhyeon.domain.entity.LoginTokenModel
import com.sopt.withsuhyeon.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService
) : LoginRepository {
    override suspend fun postLogin(phoneNumber: String): Result<LoginTokenModel> = runCatching {
        val response = loginService.postLogin(
            request = RequestLoginDto(phoneNumber)
        )
        response.result?.toLoginTokenModel() ?: throw Exception("Response data is null")
    }

    override suspend fun postPhoneNumber(phoneNumber: String): Result<Unit> = runCatching {
        val response = loginService.postLoginPhoneNumberAuth(
            request = RequestLoginDto(phoneNumber)
        )
        response.message
    }

    override suspend fun postVerifyNumber(phoneNumber: String, verifyNumber: String): Result<Unit> =
        runCatching {
            val response = loginService.postLoginVerify(
                request = RequestVerifyDto(
                    phoneNumber,
                    verifyNumber
                )
            )
            response.message
        }
}