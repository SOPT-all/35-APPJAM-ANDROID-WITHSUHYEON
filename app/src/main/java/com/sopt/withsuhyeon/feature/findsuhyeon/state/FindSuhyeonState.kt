package com.sopt.withsuhyeon.feature.findsuhyeon.state

import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_20_TO_24
import com.sopt.withsuhyeon.domain.entity.PostItemModel

data class FindSuhyeonState(
    val selectedLocation: Pair<String?, String?>? = null,
    val selectedMainLocation: String? = null,
    val selectedSubLocation: String? = null,
    val isLocationBottomSheetVisible: Boolean = false,
    val mainLocationList: List<String> = listOf(
        "전국", "서울", "부산", "제주", "인천", "강원", "경기", "경상"
    ),
    val subLocationList: List<List<String>> = listOf(
        listOf("강남/역삼/삼성", "신사/청담/압구정", "서초/교대/사당/동작", "잠실/송파/강동"),
        listOf("해운대/마린시티", "벡스코/센텀시티")
    ),
    val selectedDate: String = "전체",
    val dateList: List<String> = listOf(
        "전체", "1/25 (토)", "1/26 (일)", "1/27 (월)", "1/28 (화)"
    ),
    val postList: List<PostItemModel> = List(9) {
        PostItemModel(
            postId = it.toLong(),
            title = "서울역 수현이 구해요ㅠㅠ",
            price = 5000,
            gender = true,
            age = AGE_20_TO_24,
            date = "1월 25일 (토) 오후 2:30",
            matching = true
        )
    }
)
