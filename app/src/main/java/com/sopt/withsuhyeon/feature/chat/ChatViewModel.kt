package com.sopt.withsuhyeon.feature.chat

import androidx.lifecycle.ViewModel
import com.sopt.withsuhyeon.domain.entity.ChatRoomModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
) : ViewModel() {

    private val _chatRooms = MutableStateFlow<List<ChatRoomModel>>(emptyList())
    val chatRooms: StateFlow<List<ChatRoomModel>> get() = _chatRooms

    init {
        _chatRooms.value = listOf(
            ChatRoomModel(
                profileImage = "https://via.placeholder.com/150",
                partnerName = "작심이 친구",
                recentChat = "나는야 작심이 나는야 작심이 나는야 작심이 나는야 작심이",
                recentChatDateTime = "1월 2일",
                unreadChatCount = "5"
            ),
            ChatRoomModel(
                profileImage = "https://via.placeholder.com/150",
                partnerName = "작심이 친구",
                recentChat = "나는야 작심이 나는야 작심이 나는야 작심이 나는야 작심이",
                recentChatDateTime = "1월 2일",
                unreadChatCount = "12"
            ),
            ChatRoomModel(
                profileImage = "https://via.placeholder.com/150",
                partnerName = "작심이 친구",
                recentChat = "나는야 작심이 나는야 작심이 나는야 작심이 나는야 작심이",
                recentChatDateTime = "1월 2일",
                unreadChatCount = "+99"
            ),
            ChatRoomModel(
                profileImage = "https://via.placeholder.com/150",
                partnerName = "작심이 친구",
                recentChat = "나는야 작심이 나는야 작심이 나는야 작심이 나는야 작심이",
                recentChatDateTime = "1월 2일",
                unreadChatCount = "+99"
            ),
            ChatRoomModel(
                profileImage = "https://via.placeholder.com/150",
                partnerName = "작심이 친구",
                recentChat = "나는야 작심이 나는야 작심이 나는야 작심이 나는야 작심이",
                recentChatDateTime = "1월 2일",
                unreadChatCount = "+99"
            ),
            ChatRoomModel(
                profileImage = "https://via.placeholder.com/150",
                partnerName = "작심이 친구",
                recentChat = "나는야 작심이 나는야 작심이 나는야 작심이 나는야 작심이",
                recentChatDateTime = "1월 2일",
                unreadChatCount = "+99"
            ),
            ChatRoomModel(
                profileImage = "https://via.placeholder.com/150",
                partnerName = "작심이 친구",
                recentChat = "나는야 작심이 나는야 작심이 나는야 작심이 나는야 작심이",
                recentChatDateTime = "1월 2일",
                unreadChatCount = "+99"
            ),
            ChatRoomModel(
                profileImage = "https://via.placeholder.com/150",
                partnerName = "작심이 친구",
                recentChat = "나는야 작심이 나는야 작심이 나는야 작심이 나는야 작심이",
                recentChatDateTime = "1월 2일",
                unreadChatCount = "+99"
            ),
            ChatRoomModel(
                profileImage = "https://via.placeholder.com/150",
                partnerName = "작심이 친구",
                recentChat = "나는야 작심이 나는야 작심이 나는야 작심이 나는야 작심이",
                recentChatDateTime = "1월 2일",
                unreadChatCount = "+99"
            ),
            ChatRoomModel(
                profileImage = "https://via.placeholder.com/150",
                partnerName = "작심이 친구",
                recentChat = "나는야 작심이 나는야 작심이 나는야 작심이 나는야 작심이",
                recentChatDateTime = "1월 2일",
                unreadChatCount = "98"
            ),
        )
    }
}