package com.sopt.withsuhyeon.domain.repository

import com.sopt.withsuhyeon.domain.entity.FindSuhyeonAllPostModel
import com.sopt.withsuhyeon.domain.entity.RegionListModel

interface FindSuhyeonRepository {
    suspend fun getFindSuhyeonAllPost(regionType: String, date: String) : Result<FindSuhyeonAllPostModel>
    suspend fun getRegionList() : Result<RegionListModel>
}