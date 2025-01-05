package com.sopt.withsuhyeon.domain.repository

import com.sopt.withsuhyeon.domain.entity.DummyModel

interface DummyRepository {
    suspend fun getProductReviews(productId: Number): Result<List<DummyModel>>
    suspend fun postProductsLike(productId : Int, memberId : Int): Result<String?>
}