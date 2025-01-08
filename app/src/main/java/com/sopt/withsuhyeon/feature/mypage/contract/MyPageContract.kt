package com.sopt.withsuhyeon.feature.mypage.contract

import com.sopt.withsuhyeon.core.base.UiEvent
import com.sopt.withsuhyeon.core.base.UiSideEffect
import com.sopt.withsuhyeon.core.base.UiState

class MyPageContract {
    data class MyPageUiState(
        val isLoading: Boolean = false,
    ) : UiState

    sealed interface MyPageSideEffect : UiSideEffect {
    }

    sealed interface MyPageEvent : UiEvent {
    }
}