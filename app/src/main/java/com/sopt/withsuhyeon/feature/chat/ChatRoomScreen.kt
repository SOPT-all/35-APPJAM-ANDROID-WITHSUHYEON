    package com.sopt.withsuhyeon.feature.chat

    import android.util.Log
    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.PaddingValues
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.imePadding
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.items
    import androidx.compose.foundation.lazy.rememberLazyListState
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.LaunchedEffect
    import androidx.compose.runtime.getValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.hilt.navigation.compose.hiltViewModel
    import androidx.lifecycle.compose.collectAsStateWithLifecycle
    import com.sopt.withsuhyeon.R
    import com.sopt.withsuhyeon.core.component.card.ChatRoomInfoCardItem
    import com.sopt.withsuhyeon.core.component.chat.ChatBubble
    import com.sopt.withsuhyeon.core.component.textfield.ChatRoomTextFieldRow
    import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar
    import com.sopt.withsuhyeon.domain.entity.ChatRoomInfoModel
    import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
    import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
    import java.time.LocalTime
    import java.time.format.DateTimeFormatter
    import java.util.Locale

    @Composable
    fun ChatRoomRoute(
        padding: PaddingValues,
        chatRoomInfoModel: ChatRoomInfoModel?
    ) {
        ChatRoomScreen(
            padding = padding,
            chatRoomInfoModel = chatRoomInfoModel
        )
    }

    @Composable
    private fun ChatRoomScreen(
        padding: PaddingValues,
        chatRoomInfoModel: ChatRoomInfoModel?,
        modifier: Modifier = Modifier,
        viewModel: ChatRoomViewModel = hiltViewModel()
    ) {

        val messages by viewModel.message.collectAsStateWithLifecycle()
        Log.e("chatRoomInfoModel", "${chatRoomInfoModel?.ownerId}, ${chatRoomInfoModel?.writerId}")

        val inputText by viewModel.inputText.collectAsStateWithLifecycle()

        val lazyListState = rememberLazyListState()

        LaunchedEffect(Unit) {
            viewModel.updateChatRoomInfo(chatRoomInfoModel!!)
            viewModel.joinChatRoom()
        }

        LaunchedEffect(messages) {
            if (messages.isNotEmpty()) {
                lazyListState.animateScrollToItem(messages.size - 1)
            }
        }

        fun getCurrentTime(): String {
            val now = LocalTime.now()
            val formatter = DateTimeFormatter.ofPattern("a hh:mm", Locale("ko", "KR"))
            return now.format(formatter).replace("AM", "오전").replace("PM", "오후")
        }
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(colors.White)
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.White)
                    .align(Alignment.TopCenter)
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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 140.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    state = lazyListState,
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(messages) { (message, time, isMine) ->
                        ChatBubble(
                            message = message,
                            time = time,
                            isSentByOwner = isMine
                        )
                    }
                }
                ChatRoomTextFieldRow(
                    text = inputText,
                    onTextChange = { viewModel.updateInputText(it) },
                    onSendClick = {
                        viewModel.sendMessage()
                    },
                    modifier = Modifier.fillMaxWidth().imePadding()
                )
            }
        }
    }

    @Preview
    @Composable
    private fun ChatRoomScreenPreview() {
        WithSuhyeonTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.White)
            ) {
                ChatBubble(
                    message = "지금은 몇시일까요 지금은 몇시일까요 지금은 몇시일까요 지금은 몇시일까요 지금은 몇시일까요",
                    time = "오후 04:01",
                    isSentByOwner = false
                )
                ChatBubble(
                    message = "시러요",
                    time = "오후 04:01",
                    isSentByOwner = true
                )
                ChatBubble(
                    message = "히히",
                    time = "오후 08:40",
                    isSentByOwner = false
                )
            }
        }
    }