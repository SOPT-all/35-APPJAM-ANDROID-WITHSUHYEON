package com.sopt.withsuhyeon.domain.repository

import com.sopt.withsuhyeon.domain.entity.BlockUser

interface BlockUserRepository {
    suspend fun getBlockUser(): Result<BlockUser>
    suspend fun postBlockUser(phoneNumber: String): Result<Unit>
    suspend fun deleteBlockUser(number: String): Result<Unit>
}