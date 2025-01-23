package com.sopt.withsuhyeon.data.repositoryimpl

import com.sopt.withsuhyeon.data.mapper.toGalleryPostDetailModel
import com.sopt.withsuhyeon.data.service.GalleryService
import com.sopt.withsuhyeon.domain.entity.Category
import com.sopt.withsuhyeon.domain.entity.Gallery
import com.sopt.withsuhyeon.domain.entity.GalleryPostDetailModel
import com.sopt.withsuhyeon.domain.repository.GalleryRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val galleryService: GalleryService
) : GalleryRepository {
    override suspend fun getGalleryCategories(): Result<List<Category>> =
        runCatching {
            val response = galleryService.getGalleryCategories()
            response.result?.categories ?: throw Exception("Response data is null")
        }

    override suspend fun getGalleryTotal(category: String): Result<List<Gallery>> =
        runCatching {
            val response = galleryService.getGalleryTotal(category)
            response.result?.galleries ?: throw Exception("Response data is null")
        }

    override suspend fun getAllGalleries(): Result<List<Gallery>> =
        runCatching {
            val response = galleryService.getGalleryTotal("")
            response.result?.galleries ?: throw Exception("Response data is null")
        }

    override suspend fun uploadGallery(image: MultipartBody.Part, request: RequestBody): Result<Unit> =
        runCatching {
            val response = galleryService.uploadGallery(image, request)
            response.result
        }

    override suspend fun getGalleryPostDetail(galleryId: Long): Result<GalleryPostDetailModel> =
        runCatching {
            val response = galleryService.getGalleryPostDetail(galleryId)
            response.result?.toGalleryPostDetailModel() ?: throw Exception("Response data is null")
        }

    override suspend fun deleteGalleryPost(galleryId: Long): Result<Unit> =
        runCatching {
            val response = galleryService.deleteGalleryPost(galleryId)
            response.result
        }
}