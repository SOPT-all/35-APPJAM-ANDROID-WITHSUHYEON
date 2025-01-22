package com.sopt.withsuhyeon.domain.repository


interface SignUpRepository {
    suspend fun postPhoneNumber(phoneNumber: String): Result<Unit>
}