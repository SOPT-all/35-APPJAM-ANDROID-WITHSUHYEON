package com.sopt.withsuhyeon.domain.repository

interface LoginRepository {
    suspend fun postPhoneNumber(phoneNumber: String): Result<Unit>
    suspend fun postVerifyNumber(phoneNumber: String, verifyNumber: String): Result<Unit>
    suspend fun postLogin(phoneNumber: String): Result<Unit>
}