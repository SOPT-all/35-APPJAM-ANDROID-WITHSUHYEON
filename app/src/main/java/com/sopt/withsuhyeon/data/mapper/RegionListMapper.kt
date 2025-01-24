package com.sopt.withsuhyeon.data.mapper


import com.sopt.withsuhyeon.data.dto.response.ResponseRegionListDto
import com.sopt.withsuhyeon.domain.entity.RegionListModel

fun ResponseRegionListDto.toRegionListModel(): RegionListModel {
    return RegionListModel(
        regions = this.regions
    )
}