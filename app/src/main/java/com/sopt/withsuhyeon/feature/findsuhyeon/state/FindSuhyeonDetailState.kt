package com.sopt.withsuhyeon.feature.findsuhyeon.state

data class FindSuhyeonDetailState(
    val titleValue: String = "",
    val isTitleValid: Boolean = true,
    val titleErrorMessage: String = "",
    val findSuhyeonContentValue: String = "",
    val progress: Float = 1f / 7 * 6
)