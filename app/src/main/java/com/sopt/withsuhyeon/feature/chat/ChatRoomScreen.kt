package com.sopt.withsuhyeon.feature.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.card.ChatRoomInfoCardItem
import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

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
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(padding)
            .background(colors.White)
    ) {
        SubTopNavBar(
            text = "작심이",
            btnIcon = painterResource(R.drawable.ic_close),
            isTextVisible = true,
            isBtnVisible = false,
            onCloseBtnClicked = {}
        )
        ChatRoomInfoCardItem(
            region = "강남/역삼/삼성",
            postTitle = "강남역에서 수현이 되어주실 분!",
            price = 20000
        )
    }
}

@Preview
@Composable
private fun ChatRoomScreenPreview() {
    WithSuhyeonTheme {
        ChatRoomScreen(
            padding = PaddingValues()
        )
    }
}