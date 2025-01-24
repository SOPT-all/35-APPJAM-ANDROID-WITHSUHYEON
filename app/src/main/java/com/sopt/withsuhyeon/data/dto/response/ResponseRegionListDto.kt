package com.sopt.withsuhyeon.data.dto.response

import com.sopt.withsuhyeon.domain.entity.LocationListModel
import kotlinx.serialization.Serializable

@Serializable
data class ResponseRegionListDto (
    val regions: List<LocationListModel>
)