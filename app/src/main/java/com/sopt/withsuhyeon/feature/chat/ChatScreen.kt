package com.sopt.withsuhyeon.feature.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.list.TotalChatRoomList
import com.sopt.withsuhyeon.core.component.topbar.MainTopNavBar
import com.sopt.withsuhyeon.data.dto.response.ChatRoom
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun ChatRoute(
    padding: PaddingValues,
    navigateToChatRoomReal: (ChatRoom) -> Unit,
    viewModel: ChatViewModel = hiltViewModel()
) {
    ChatScreen(
        padding = padding,
        onChatRoomListItemClick = {
            navigateToChatRoomReal(viewModel.chatRooms.value[it])
        }
    )
}
@Composable
private fun ChatScreen(
    padding: PaddingValues,
    onChatRoomListItemClick: (Int) -> Unit,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val sampleChatRooms by viewModel.chatRooms.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getChatRooms()
        viewModel.receiveChatRooms()
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(padding)
            .background(colors.Grey50),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        MainTopNavBar(
            text = stringResource(R.string.total_chatroom_top_nav_bar_title)
        )

        HorizontalDivider(
            thickness = 1.dp,
            color = colors.Grey100
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            TotalChatRoomList(
                chatRooms = sampleChatRooms,
                onChatRoomListItemClick = onChatRoomListItemClick
            )
        }
    }
}