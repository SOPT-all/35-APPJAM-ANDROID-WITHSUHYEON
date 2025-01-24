package com.sopt.withsuhyeon.data.repositoryimpl

import com.sopt.withsuhyeon.data.mapper.toHomeModel
import com.sopt.withsuhyeon.data.service.HomeService
import com.sopt.withsuhyeon.domain.entity.HomeModel
import com.sopt.withsuhyeon.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService
) : HomeRepository {
    override suspend fun getHomeData(): Result<HomeModel> =
        runCatching {
            val response = homeService.getHomeData()
            response.result?.toHomeModel() ?: throw Exception("Response data is null")
        }
}