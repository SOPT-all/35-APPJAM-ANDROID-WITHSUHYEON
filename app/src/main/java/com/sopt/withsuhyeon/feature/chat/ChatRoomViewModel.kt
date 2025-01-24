package com.sopt.withsuhyeon.feature.chat

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.data.dto.request.RequestChatDto
import com.sopt.withsuhyeon.data.socket.WebSocketClient
import com.sopt.withsuhyeon.domain.entity.ChatRoomInfoModel
import com.sopt.withsuhyeon.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class ChatRoomViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _chatRoomInfo = MutableStateFlow<ChatRoomInfoModel>(ChatRoomInfoModel(0,0,0,"",""))
    val chatRoomInfo: StateFlow<ChatRoomInfoModel> = _chatRoomInfo

    private var _message = MutableStateFlow<List<Triple<String, String, Boolean>>>(emptyList())
    val message: StateFlow<List<Triple<String, String, Boolean>>> = _message.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    val webSocketClient = WebSocketClient.getInstance()

    init {
    }

    fun updateChatRoomInfo(chatRoomInfoModel: ChatRoomInfoModel) {
        _chatRoomInfo.value = chatRoomInfoModel
    }
    fun sendMessage(input: String) {
        var newMessages = _message.value
        newMessages += Triple(input, "1:1", true)

        _message.value = newMessages
        val requestChatDto = RequestChatDto(
            ownerChatRoomId = _chatRoomInfo.value!!.ownerChatRoomId,
            peerChatRoomId = _chatRoomInfo.value!!.peerChatRoomId,
            senderId = _chatRoomInfo.value!!.ownerId.toInt(),
            receiverId = _chatRoomInfo.value!!.writerId.toInt(),
            postId = _chatRoomInfo.value!!.postId.toInt(),
            content = input,
            type = if(_chatRoomInfo?.value!!.ownerChatRoomId == "NO") "CREATE" else "MESSAGE"
        )

        val jsonString = Json.encodeToString(RequestChatDto.serializer(), requestChatDto)
        webSocketClient.sendWebSocketMessage(requestChatDto, RequestChatDto.serializer())
    }

    fun joinChatRoom() {
        if(_chatRoomInfo.value!!.ownerChatRoomId == "NO"){

            Log.e("되냐???1", "히융")
            collectChatCreate()
            return
        }
        viewModelScope.launch {
            chatRepository.patchEnterChatRoom(_chatRoomInfo?.value!!.ownerChatRoomId).onSuccess {

                Log.e("되냐???2", "히융")
                collectChat()
            }
        }
    }

    fun collectChatCreate() {
        viewModelScope.launch {
            webSocketClient.subscribeCreateChat().collect {
                _chatRoomInfo.value = _chatRoomInfo.value!!.copy(ownerChatRoomId = it.ownerChatRoomId, peerChatRoomId = it.peerChatRoomId)

                Log.e("되냐???3", "히융")
                joinChatRoom()
            }
        }
    }

    fun collectChat() {
        viewModelScope.launch {
            webSocketClient.subscribeChatMessage().collect {
                var newList = _message.value
                newList += Triple(it.content, it.timestamp, false)

                Log.e("되냐???4", "히융")
                _message.emit(newList)
            }
        }
    }
}