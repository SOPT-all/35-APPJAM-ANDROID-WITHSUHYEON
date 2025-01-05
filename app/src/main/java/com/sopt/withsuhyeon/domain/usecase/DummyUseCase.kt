package com.sopt.withsuhyeon.domain.usecase

import com.sopt.withsuhyeon.domain.repository.DummyRepository
import javax.inject.Inject

class DummyUseCase @Inject constructor(
    private val dummyRepository: DummyRepository
) {
    suspend operator fun invoke(productId: Int, memberId: Int): Result<String?> {
        return dummyRepository.postProductsLike(productId, memberId)
    }
}