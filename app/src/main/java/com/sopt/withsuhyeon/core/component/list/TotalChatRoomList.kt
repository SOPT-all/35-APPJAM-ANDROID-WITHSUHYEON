package com.sopt.withsuhyeon.core.component.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sopt.withsuhyeon.core.component.listitem.ChatRoomListItem
import com.sopt.withsuhyeon.data.dto.response.ChatRoom
import com.sopt.withsuhyeon.domain.entity.ChatRoomDetailModel
import com.sopt.withsuhyeon.domain.entity.ChatRoomListInfoModel

@Composable
fun TotalChatRoomList(
    chatRooms: List<ChatRoom>,
    onChatRoomListItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        itemsIndexed(chatRooms) { index, chatRoom ->
            ChatRoomListItem(
                profileImage = chatRoom.chatPeerProfileImage,
                partnerName = chatRoom.chatPeerNickname,
                recentChat = chatRoom.lastChatMessage,
                recentChatDateTime = chatRoom.lastChatAt,
                unreadChatCount = chatRoom.unReadCount,
                onChatRoomListItemClick = {
                    onChatRoomListItemClick(index)
                }
            )
        }
    }
}