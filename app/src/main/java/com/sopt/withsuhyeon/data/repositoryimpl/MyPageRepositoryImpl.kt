package com.sopt.withsuhyeon.data.repositoryimpl

import com.sopt.withsuhyeon.data.mapper.toMyPageInfoModel
import com.sopt.withsuhyeon.data.service.MyPageService
import com.sopt.withsuhyeon.domain.entity.MyPageInfo
import com.sopt.withsuhyeon.domain.repository.MyPageRepository
import javax.inject.Inject

class MyPageRepositoryImpl @Inject constructor(
    private val myPageService: MyPageService
) : MyPageRepository {
    override suspend fun getMyPageInfo(): Result<MyPageInfo> =
        runCatching {
            val response = myPageService.getMyPageInfo()
            response.result?.toMyPageInfoModel() ?: throw Exception("Response data is null")
        }
}