package com.sopt.withsuhyeon.domain.repository

import com.sopt.withsuhyeon.domain.entity.FindSuhyeonAllPostModel
import com.sopt.withsuhyeon.domain.entity.FindSuhyeonPostDetailModel
import com.sopt.withsuhyeon.domain.entity.FindSuhyeonPostUploadModel
import com.sopt.withsuhyeon.domain.entity.RegionListModel

interface FindSuhyeonRepository {
    suspend fun getFindSuhyeonAllPost(regionType: String, date: String) : Result<FindSuhyeonAllPostModel>
    suspend fun getRegionList() : Result<RegionListModel>
    suspend fun postFindSuhyeonUpload(request: FindSuhyeonPostUploadModel) : Result<Unit>
    suspend fun getFindSuhyeonPostDetail(postId: Long) : Result<FindSuhyeonPostDetailModel>
    suspend fun deleteFindSuhyeonPost(postId: Long) : Result<Unit>
}