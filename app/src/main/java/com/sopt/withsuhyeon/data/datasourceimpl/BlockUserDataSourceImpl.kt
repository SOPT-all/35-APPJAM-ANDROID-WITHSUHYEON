package com.sopt.withsuhyeon.data.datasourceimpl

import com.sopt.withsuhyeon.data.datasource.BlockUserDataSource
import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.request.RequestBlockUserDto
import com.sopt.withsuhyeon.data.dto.response.ResponseBlockUserDto
import com.sopt.withsuhyeon.data.service.BlockUserService
import javax.inject.Inject

class BlockUserDataSourceImpl @Inject constructor(
    private val blockUserService: BlockUserService
) : BlockUserDataSource {
    override suspend fun getBlockUser(): BaseResponse<ResponseBlockUserDto> =
        blockUserService.getBlockUser()

    override suspend fun postBlockUser(phoneNumber: String): BaseResponse<Unit> =
        blockUserService.postBlockUser(request = RequestBlockUserDto(phoneNumber))


    override suspend fun deleteBlockUser(number: String): BaseResponse<Unit> =
        blockUserService.deleteBlockUser(number)
}