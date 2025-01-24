package com.sopt.withsuhyeon.domain.repository

interface UserRepository {
    suspend fun connectWithUserId() : Result<Unit>
}