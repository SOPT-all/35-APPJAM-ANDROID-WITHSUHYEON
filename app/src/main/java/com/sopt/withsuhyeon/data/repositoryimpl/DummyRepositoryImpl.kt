package com.sopt.withsuhyeon.data.repositoryimpl

import com.sopt.withsuhyeon.data.datasource.DummyDataSource
import com.sopt.withsuhyeon.data.mapper.toDummyListModel
import com.sopt.withsuhyeon.data.mapper.toUserModel
import com.sopt.withsuhyeon.domain.entity.DummyModel
import com.sopt.withsuhyeon.domain.entity.UserModel
import com.sopt.withsuhyeon.domain.repository.DummyRepository
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    private val dummyDataSource: DummyDataSource
) : DummyRepository {
    override suspend fun getProductReviews(productId: Number): Result<List<DummyModel>> =
        runCatching {
            val response = dummyDataSource.getProductReviews(productId)
            response.data?.toDummyListModel() ?: emptyList()
        }

    override suspend fun postProductsLike(productId: Int, memberId: Int): Result<String?> =
        runCatching {
            val response = dummyDataSource.postProductsLike(productId, memberId)
            response.data
        }

    override suspend fun getUserDetails(userId: Int): Result<UserModel> =
        runCatching {
            val response = dummyDataSource.getUserDetails(userId)
            response.data.toUserModel()
        }
}