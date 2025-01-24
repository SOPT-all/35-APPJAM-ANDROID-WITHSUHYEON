package com.sopt.withsuhyeon.feature.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.data.dto.response.ChatRoom
import com.sopt.withsuhyeon.data.socket.WebSocketClient
import com.sopt.withsuhyeon.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {

    private val webSocketClient = WebSocketClient.getInstance()
    private val _chatRooms = MutableStateFlow<List<ChatRoom>>(emptyList())
    val chatRooms: StateFlow<List<ChatRoom>> = _chatRooms.asStateFlow()

    fun getChatRooms() {
        viewModelScope.launch {
            chatRepository.getTotalChatRooms().onSuccess {
                _chatRooms.emit(it)
            }.onFailure {

            }
        }
    }

    fun receiveChatRooms() {
        viewModelScope.launch {
            webSocketClient.subscribeChatRooms().collect {
                _chatRooms.emit(it.chatRooms)
            }
        }
    }
}