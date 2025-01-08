package com.sopt.withsuhyeon.feature.chat

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

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
    padding: PaddingValues
) {

}