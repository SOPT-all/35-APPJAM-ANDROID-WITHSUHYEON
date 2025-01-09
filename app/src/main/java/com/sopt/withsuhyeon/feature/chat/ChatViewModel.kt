package com.sopt.withsuhyeon.feature.chat

import com.sopt.withsuhyeon.core.base.BaseViewModel
import com.sopt.withsuhyeon.feature.chat.contract.ChatContract.ChatEvent
import com.sopt.withsuhyeon.feature.chat.contract.ChatContract.ChatSideEffect
import com.sopt.withsuhyeon.feature.chat.contract.ChatContract.ChatUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
) : BaseViewModel<ChatUiState, ChatSideEffect, ChatEvent>() {
    override fun createInitialState(): ChatUiState {
        return ChatUiState()
    }

    override suspend fun handleEvent(event: ChatEvent) {
        when(event) {
            else -> {}
        }
    }

}