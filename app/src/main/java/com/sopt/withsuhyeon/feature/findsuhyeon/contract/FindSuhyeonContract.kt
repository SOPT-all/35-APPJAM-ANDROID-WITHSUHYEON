package com.sopt.withsuhyeon.feature.findsuhyeon.contract

import com.sopt.withsuhyeon.core.base.UiEvent
import com.sopt.withsuhyeon.core.base.UiSideEffect
import com.sopt.withsuhyeon.core.base.UiState

class FindSuhyeonContract {
    data class FindSuhyeonUiState(
        val isLoading: Boolean = false,
    ) : UiState

    sealed interface FindSuhyeonSideEffect : UiSideEffect {
    }

    sealed interface FindSuhyeonEvent : UiEvent {
    }
}