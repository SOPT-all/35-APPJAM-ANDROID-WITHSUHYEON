package com.sopt.withsuhyeon.feature.findsuhyeon.state

data class FindSuhyeonDetailState(
    val titleValue: String = "",
    val isTitleValid: Boolean = true,
    val isTitleFocused: Boolean = false,
    val titleErrorMessage: String = "",
    val contentValue: String = "",
    val contentErrorMessage: String = "",
    val isContentValid: Boolean = true,
    val isContentFocused: Boolean = false,
    val progress: Float = 1f / 7 * 6,
    val isDeleteAlertModalVisible: Boolean = false
)