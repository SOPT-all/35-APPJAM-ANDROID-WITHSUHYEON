package com.sopt.withsuhyeon.feature.chat

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ChatRoomRoute(
    padding: PaddingValues
) {
    ChatRoomScreen(
        padding = padding
    )
}

@Composable
private fun ChatRoomScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = hiltViewModel()
) {

}