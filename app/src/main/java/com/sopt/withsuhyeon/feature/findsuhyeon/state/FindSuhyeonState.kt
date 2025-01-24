package com.sopt.withsuhyeon.feature.findsuhyeon.state

import com.sopt.withsuhyeon.domain.entity.FindSuhyeonAllPostModel
import com.sopt.withsuhyeon.domain.entity.RegionListModel

data class FindSuhyeonState(
    val findSuhyeonData: FindSuhyeonAllPostModel = FindSuhyeonAllPostModel(
        region = "",
        days = listOf(""),
        posts = emptyList()
    ),
    val regionList: RegionListModel = RegionListModel(
        regions = emptyList()
    ),
    val selectedLocation: Pair<String?, String?>? = null,
    val selectedMainLocation: String? = null,
    val selectedSubLocation: String? = "",
    val isLocationBottomSheetVisible: Boolean = false,
    val mainLocationList: List<String> = emptyList(),
    val subLocationList: List<List<String>> = listOf(emptyList()),
    val selectedDate: String = "",
    val isRefreshing: Boolean = false,
)
