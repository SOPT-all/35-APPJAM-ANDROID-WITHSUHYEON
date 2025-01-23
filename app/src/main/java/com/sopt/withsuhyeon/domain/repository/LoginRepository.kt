package com.sopt.withsuhyeon.domain.repository

import com.sopt.withsuhyeon.domain.entity.LoginTokenModel

interface LoginRepository {
    suspend fun postPhoneNumber(phoneNumber: String): Result<Unit>
    suspend fun postVerifyNumber(phoneNumber: String, verifyNumber: String): Result<Unit>
    suspend fun postLogin(phoneNumber: String): Result<LoginTokenModel>
}