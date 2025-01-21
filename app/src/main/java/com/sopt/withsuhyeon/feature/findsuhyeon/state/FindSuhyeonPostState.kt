package com.sopt.withsuhyeon.feature.findsuhyeon.state

import com.sopt.withsuhyeon.domain.entity.PostDetailInfoModel

data class FindSuhyeonPostState(
    val postTitle: String = "",
    val postDescription: String = "",
    val profileImage: String = "",
    val userName: String = "",
    val date: String = "",
    val detailInfo: PostDetailInfoModel? = null,
    val isDeleteBottomSheetVisible: Boolean = false,
    val isDeleteAlertModalVisible: Boolean = false
)