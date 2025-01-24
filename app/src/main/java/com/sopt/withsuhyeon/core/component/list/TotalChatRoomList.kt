package com.sopt.withsuhyeon.core.component.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sopt.withsuhyeon.core.component.listitem.ChatRoomListItem
import com.sopt.withsuhyeon.domain.entity.ChatRoomListInfoModel

@Composable
fun TotalChatRoomList(
    chatRooms: List<ChatRoomListInfoModel>,
    onChatRoomListItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(chatRooms) { chatRoom ->
            ChatRoomListItem(
                profileImage = chatRoom.profileImage,
                partnerName = chatRoom.partnerName,
                recentChat = chatRoom.recentChat,
                recentChatDateTime = chatRoom.recentChatDateTime,
                unreadChatCount = chatRoom.unreadChatCount,
                onChatRoomListItemClick = onChatRoomListItemClick
            )
        }
    }
}