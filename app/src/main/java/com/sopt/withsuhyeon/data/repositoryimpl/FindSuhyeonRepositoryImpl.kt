package com.sopt.withsuhyeon.data.repositoryimpl

import com.sopt.withsuhyeon.data.mapper.toFindSuhyeonAllPostModel
import com.sopt.withsuhyeon.data.mapper.toRegionListModel
import com.sopt.withsuhyeon.data.service.FindSuhyeonService
import com.sopt.withsuhyeon.domain.entity.FindSuhyeonAllPostModel
import com.sopt.withsuhyeon.domain.entity.RegionListModel
import com.sopt.withsuhyeon.domain.repository.FindSuhyeonRepository
import javax.inject.Inject

class FindSuhyeonRepositoryImpl @Inject constructor(
    private val findSuhyeonService: FindSuhyeonService
) : FindSuhyeonRepository {
    override suspend fun getFindSuhyeonAllPost(regionType: String, date: String): Result<FindSuhyeonAllPostModel> =
        runCatching {
            val response = findSuhyeonService.getFindSuhyeonAllPost(regionType, date)
            response.result?.toFindSuhyeonAllPostModel() ?: throw Exception("Response data is null")
        }

    override suspend fun getRegionList(): Result<RegionListModel> =
        runCatching {
            val response = findSuhyeonService.getRegionList()
            response.result?.toRegionListModel() ?: throw Exception("Response data is null")
        }
}