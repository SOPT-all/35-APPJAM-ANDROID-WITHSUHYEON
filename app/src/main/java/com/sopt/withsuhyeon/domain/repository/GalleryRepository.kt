package com.sopt.withsuhyeon.domain.repository

import com.sopt.withsuhyeon.domain.entity.Category

interface GalleryRepository {
    suspend fun getGalleryCategories() : Result<List<Category>>
}