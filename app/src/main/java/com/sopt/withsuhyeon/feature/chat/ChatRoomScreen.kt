package com.sopt.withsuhyeon.feature.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.card.ChatRoomInfoCardItem
import com.sopt.withsuhyeon.core.component.chat.ChatBubble
import com.sopt.withsuhyeon.core.component.textfield.ChatRoomTextFieldRow
import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

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
    val messages = remember { mutableStateListOf<Triple<String, Boolean, String>>() }
    val (inputText, setInputText) = remember { mutableStateOf("") }

    fun getCurrentTime(): String {
        val now = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("a hh:mm", Locale("ko", "KR"))
        return now.format(formatter).replace("AM", "오전").replace("PM", "오후")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.White)
            .padding(padding)
    ) {
        Column(
            modifier = Modifier
                .background(colors.White)
                .weight(1f)
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                messages.forEach { (message, isSentByOwner, time) ->
                    ChatBubble(
                        message = message,
                        time = time,
                        isSentByOwner = isSentByOwner
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .imePadding()
        ) {
            ChatRoomTextFieldRow(
                text = inputText,
                onTextChange = setInputText,
                onSendClick = {
                    if (inputText.isNotBlank()) {
                        val currentTime = getCurrentTime()
                        messages.add(Triple(inputText, true, currentTime))
                        setInputText("")
                    }
                },
                modifier = Modifier.fillMaxWidth()
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