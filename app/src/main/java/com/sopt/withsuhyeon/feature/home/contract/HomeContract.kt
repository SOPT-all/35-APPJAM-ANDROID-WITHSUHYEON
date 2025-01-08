package com.sopt.withsuhyeon.feature.home.contract

import com.sopt.withsuhyeon.core.base.UiEvent
import com.sopt.withsuhyeon.core.base.UiSideEffect
import com.sopt.withsuhyeon.core.base.UiState

class HomeContract {
    data class HomeUiState(
        val isLoading: Boolean = false,
    ) : UiState

    sealed interface HomeSideEffect : UiSideEffect {
    }

    sealed interface HomeEvent : UiEvent {
    }
}