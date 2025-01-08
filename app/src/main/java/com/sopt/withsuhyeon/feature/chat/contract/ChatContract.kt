package com.sopt.withsuhyeon.feature.chat.contract

import com.sopt.withsuhyeon.core.base.UiEvent
import com.sopt.withsuhyeon.core.base.UiSideEffect
import com.sopt.withsuhyeon.core.base.UiState

class ChatContract {
    data class ChatUiState(
        val isLoading: Boolean = false,
    ) : UiState

    sealed interface ChatSideEffect : UiSideEffect {
    }

    sealed interface ChatEvent : UiEvent {
    }
}