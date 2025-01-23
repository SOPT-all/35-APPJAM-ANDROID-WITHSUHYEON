package com.sopt.withsuhyeon.domain.repository

import com.sopt.withsuhyeon.data.dto.request.RequestBlockUserDto
import com.sopt.withsuhyeon.domain.entity.BlockUserModel

interface BlockUserRepository {
    suspend fun getBlockUser(): Result<BlockUserModel>
    suspend fun postBlockUser(phoneNumber: String): Result<Unit>
    suspend fun deleteBlockUser(number: String): Result<Unit>
}