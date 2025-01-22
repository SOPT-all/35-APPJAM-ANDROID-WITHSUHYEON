package com.sopt.withsuhyeon.domain.repository

import com.sopt.withsuhyeon.domain.entity.Category
import com.sopt.withsuhyeon.domain.entity.Gallery
import com.sopt.withsuhyeon.domain.entity.GalleryPostDetailModel
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface GalleryRepository {
    suspend fun getGalleryCategories() : Result<List<Category>>
    suspend fun getGalleryTotal(category: String) : Result<List<Gallery>>
    suspend fun uploadGallery(image: MultipartBody.Part, request: RequestBody) : Result<Unit>
    suspend fun getGalleryPostDetail(galleryId: Long) : Result<GalleryPostDetailModel>
}