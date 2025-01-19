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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.list.TotalChatRoomList
import com.sopt.withsuhyeon.core.component.topbar.MainTopNavBar
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun ChatRoute(
    padding: PaddingValues,
    viewModel: ChatViewModel = hiltViewModel()
) {
    ChatScreen(
        padding = padding
    )
}
@Composable
private fun ChatScreen(
    padding: PaddingValues,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val sampleChatRooms by viewModel.chatRooms.collectAsState()

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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            TotalChatRoomList(chatRooms = sampleChatRooms)
        }
    }
}