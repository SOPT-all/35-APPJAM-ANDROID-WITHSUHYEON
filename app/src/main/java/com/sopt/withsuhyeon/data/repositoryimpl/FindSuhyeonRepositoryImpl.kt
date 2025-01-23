package com.sopt.withsuhyeon.data.repositoryimpl

import com.sopt.withsuhyeon.data.mapper.toFindSuhyeonAllPostModel
import com.sopt.withsuhyeon.data.mapper.toFindSuhyeonPostDetailModel
import com.sopt.withsuhyeon.data.mapper.toRegionListModel
import com.sopt.withsuhyeon.data.mapper.toRequestFindSuhyeonPostUploadDto
import com.sopt.withsuhyeon.data.service.FindSuhyeonService
import com.sopt.withsuhyeon.domain.entity.FindSuhyeonAllPostModel
import com.sopt.withsuhyeon.domain.entity.FindSuhyeonPostDetailModel
import com.sopt.withsuhyeon.domain.entity.FindSuhyeonPostUploadModel
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

    override suspend fun postFindSuhyeonUpload(request: FindSuhyeonPostUploadModel): Result<Unit> =
        runCatching {
            val response = findSuhyeonService.postFindSuhyeonUpload(request.toRequestFindSuhyeonPostUploadDto())
            response.result ?: throw Exception("Response data is null")
        }

    override suspend fun getFindSuhyeonPostDetail(postId: Long): Result<FindSuhyeonPostDetailModel> =
        runCatching {
            val response = findSuhyeonService.getFindSuhyeonPostDetail(postId)
            response.result?.toFindSuhyeonPostDetailModel() ?: throw Exception("Response data is null")
        }

    override suspend fun deleteFindSuhyeonPost(postId: Long): Result<Unit> =
        runCatching {
            val response = findSuhyeonService.deleteFindSuhyeonPost(postId)
            response.result ?: throw Exception("Response data is null")
        }

}