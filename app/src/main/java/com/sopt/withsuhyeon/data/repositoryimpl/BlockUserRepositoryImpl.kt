package com.sopt.withsuhyeon.data.repositoryimpl

import com.sopt.withsuhyeon.data.dto.request.RequestBlockUserDto
import com.sopt.withsuhyeon.data.mapper.toBlockUserModel
import com.sopt.withsuhyeon.data.service.BlockUserService
import com.sopt.withsuhyeon.domain.entity.BlockUserModel
import com.sopt.withsuhyeon.domain.repository.BlockUserRepository
import javax.inject.Inject

class BlockUserRepositoryImpl @Inject constructor(
    private val blockUserService: BlockUserService
) : BlockUserRepository {
    override suspend fun getBlockUser(): Result<BlockUserModel> =
        runCatching {
            val response = blockUserService.getBlockUser()
            response.result?.toBlockUserModel() ?: throw Exception("Response data is null")
        }

    override suspend fun postBlockUser(phoneNumber: String): Result<Unit> =
        runCatching {
            val response = blockUserService.postBlockUser(
                request = RequestBlockUserDto(phoneNumber)
            )
            response.message
        }

    override suspend fun deleteBlockUser(number: String): Result<Unit> =
        runCatching {
            val response = blockUserService.deleteBlockUser(
                number = number
            )
            response.message
        }
}