package com.sopt.withsuhyeon.feature.chat

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.data.dto.request.RequestChatDto
import com.sopt.withsuhyeon.data.socket.WebSocketClient
import com.sopt.withsuhyeon.domain.entity.ChatRoomDetailModel
import com.sopt.withsuhyeon.domain.entity.toTriple
import com.sopt.withsuhyeon.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class ChatRoomRealViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _chatRoomDetail = MutableStateFlow<ChatRoomDetailModel>(ChatRoomDetailModel("", "", 0, 0, 0, "", "", "", "", 0))
    val chatRoomDetail: StateFlow<ChatRoomDetailModel> = _chatRoomDetail

    private var _message = MutableStateFlow<List<Triple<String, String, Boolean>>>(emptyList())
    val message: StateFlow<List<Triple<String, String, Boolean>>> = _message.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _inputText = MutableStateFlow<String>("")
    val inputText: StateFlow<String> = _inputText.asStateFlow()

    val webSocketClient = WebSocketClient.getInstance()

    init {
    }
    fun updateInputText(input: String) {
        _inputText.value = input
    }

    fun updateChatRoomInfo(chatRoomDetailModel: ChatRoomDetailModel) {
        _chatRoomDetail.value = chatRoomDetailModel
    }
    fun sendMessage() {
        var newMessages = _message.value
        newMessages += Triple(inputText.value, "1:1", true)

        _message.value = newMessages
        // 이거 수정 ?
        val requestChatDto = RequestChatDto(
            ownerChatRoomId = _chatRoomDetail.value!!.ownerChatRoomId,
            peerChatRoomId = _chatRoomDetail.value!!.peerChatRoomId,
            senderId = _chatRoomDetail.value!!.chatOwnerId,
            receiverId = _chatRoomDetail.value!!.chatPeerId,
            postId = _chatRoomDetail.value!!.postId.toInt(),
            content = inputText.value,
            type = if(_chatRoomDetail?.value!!.ownerChatRoomId == "NO") "CREATE" else "MESSAGE"
        )

        val jsonString = Json.encodeToString(RequestChatDto.serializer(), requestChatDto)
        webSocketClient.sendWebSocketMessage(requestChatDto, RequestChatDto.serializer())
        _inputText.value = ""
    }

    fun joinChatRoom() {
        if(_chatRoomDetail.value!!.ownerChatRoomId == "NO"){

            Log.e("되냐???1", "히융")
            collectChatCreate()
            return
        }
        viewModelScope.launch {
            chatRepository.patchEnterChatRoom(_chatRoomDetail?.value!!.ownerChatRoomId).onSuccess {

                Log.e("되냐???2", "히융")
                collectChat()
                chatRepository.getChatRoomMessageTotal(_chatRoomDetail?.value!!.ownerChatRoomId).onSuccess {
                    _message.emit(it.map { it.toTriple() })
                }
            }
        }
    }

    fun collectChatCreate() {
        viewModelScope.launch {
            webSocketClient.subscribeCreateChat().collect {
                _chatRoomDetail.value = _chatRoomDetail.value!!.copy(ownerChatRoomId = it.ownerChatRoomId, peerChatRoomId = it.peerChatRoomId)

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