package com.sopt.withsuhyeon.domain.repository

import com.sopt.withsuhyeon.domain.entity.Category
import com.sopt.withsuhyeon.domain.entity.Gallery
import com.sopt.withsuhyeon.domain.entity.UploadGalleryModel

interface GalleryRepository {
    suspend fun getGalleryCategories() : Result<List<Category>>
    suspend fun getGalleryTotal(category: String) : Result<List<Gallery>>
    // suspend fun uploadGallery(header: String, image: String, request: UploadGalleryModel)
}