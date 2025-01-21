package com.sopt.withsuhyeon.data.repositoryimpl

import com.sopt.withsuhyeon.data.service.GalleryService
import com.sopt.withsuhyeon.domain.entity.Category
import com.sopt.withsuhyeon.domain.repository.GalleryRepository
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val galleryService: GalleryService
) : GalleryRepository {
    override suspend fun getGalleryCategories(): Result<List<Category>> =
        runCatching {
            val response = galleryService.getGalleryCategories()
            response.result?.categories ?: throw Exception("Response data is null")
        }
}