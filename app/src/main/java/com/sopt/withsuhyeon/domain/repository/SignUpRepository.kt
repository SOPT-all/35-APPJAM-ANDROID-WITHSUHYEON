package com.sopt.withsuhyeon.domain.repository


interface SignUpRepository {
    suspend fun postPhoneNumber(phoneNumber: String): Result<Unit>
    suspend fun postVerifyNumber(phoneNumber: String, verifyNumber: String): Result<Unit>
}

