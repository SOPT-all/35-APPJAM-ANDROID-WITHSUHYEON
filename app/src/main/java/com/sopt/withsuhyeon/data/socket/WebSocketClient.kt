package com.sopt.withsuhyeon.data.socket

import android.util.Log
import com.sopt.withsuhyeon.data.dto.response.ResponseChatCreateDto
import com.sopt.withsuhyeon.data.dto.response.ResponseChatDto
import com.sopt.withsuhyeon.data.dto.response.ResponseChatRoomsDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.serialization.KSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject

class WebSocketClient @Inject constructor() {
    companion object {
        @Volatile
        private var INSTANCE: WebSocketClient? = null

        fun getInstance(): WebSocketClient {
            return INSTANCE ?: synchronized(this) {
                val instance = WebSocketClient()
                INSTANCE = instance
                instance
            }
        }
    }

    private var webSocket: WebSocket? = null
    private val client = OkHttpClient()
    private val _messageFlow = MutableSharedFlow<String>()
    private var retryCount = 0
    private val maxRetryCount = 5
    private val initialRetryInterval: Long = 2
    private var isConnecting = false
    private var targetUrl: String? = null

    fun connect(url: String) {
        if (isConnecting) return
        isConnecting = true
        targetUrl = url

        val request = Request.Builder().url(url).build()
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                Log.d("WebSocket", "✅ WebSocket 연결됨: $url $response")
                retryCount = 0
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                Log.e("에이시팔", webSocket.toString())
                Log.d("WebSocket", "📩 WebSocket 수신 메시지: $text")
                CoroutineScope(Dispatchers.Main).launch {
                    _messageFlow.emit(text)
                }
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                Log.e("WebSocket", "❌ WebSocket 연결 실패: ${t.localizedMessage}")
                handleConnectionFailure()
            }
        })
    }

    fun sendMessage(message: String) {
        if (webSocket == null) {
            Log.e("WebSocket", "❌ WebSocket is not connected.")
            return
        }
        webSocket?.send(message)
        Log.d("WebSocket", "✅ WebSocket 메시지 전송됨: $message")
    }

    inline fun <reified T> send(value: T) {
        try {
            val jsonString = Json.encodeToString(value)  // JSON 문자열로 변환
            sendMessage(jsonString)
            Log.e("Web", "$jsonString")
        } catch (e: Exception) {
            Log.e("WebSocket", "❌ JSON 인코딩 실패: ${e.localizedMessage}")
        }
    }

    private fun handleConnectionFailure() {
        isConnecting = false

        if (retryCount <= maxRetryCount) {
            val delay = initialRetryInterval * Math.pow(2.0, (retryCount - 1).toDouble()).toLong()
            Log.d("WebSocket", "WebSocket 재연결 시도 (횟수: $retryCount, $delay 초 후)")

            CoroutineScope(Dispatchers.Main).launch {
                delay(delay * 1000)
                targetUrl?.let { connect(it) }
            }
        } else {
            Log.e("WebSocket", "❌ WebSocket 연결 실패: 최대 재연결 횟수 초과")
        }
    }

    fun disconnect() {
        webSocket?.close(1000, "Closed by client")
        Log.d("WebSocket", "❌ WebSocket 연결 종료")
    }

    fun observeMessages(): Flow<String> {
        return _messageFlow
        }
            /*.catch { e -> Log.e("WebSocket", "❌ WebSocket 오류: ${e.localizedMessage}") }
            .onCompletion { Log.d("WebSocket", "WebSocket 연결 종료") }*/


    fun subscribeCreateChat(): Flow<ResponseChatCreateDto> = flow {
        observeMessages().collect { message ->
            try {
                val parsedMessage = Json.decodeFromString<ResponseChatCreateDto>(message)
                Log.d("WebSocket", "Parsed ResponseChatCreateDto: $parsedMessage")
                emit(parsedMessage)
            } catch (e: Exception) {
                Log.e("WebSocket", "Failed to parse message: ${e.localizedMessage}")
            }
        }
    }.flowOn(Dispatchers.IO)

    fun subscribeChatMessage(): Flow<ResponseChatDto> = flow {
        observeMessages().collect { message ->
            try {
                val parsedMessage = Json.decodeFromString<ResponseChatDto>(message)
                Log.d("WebSocket", "Parsed ResponseChatDto: $parsedMessage")
                emit(parsedMessage)
            } catch (e: Exception) {
                Log.e("WebSocket", "Failed to parse chat message: ${e.localizedMessage}")
            }
        }
    }.flowOn(Dispatchers.IO)

    fun subscribeChatRooms(): Flow<ResponseChatRoomsDto> = flow {
        observeMessages().collect { message ->
            try {
                val parsedMessage = Json.decodeFromString<ResponseChatRoomsDto>(message)
                Log.d("WebSocket", "Parsed ResponseChatRoomsDto: $parsedMessage")
                emit(parsedMessage)
            } catch (e: Exception) {
                Log.e("WebSocket", "Failed to parse chat rooms: ${e.localizedMessage}")
            }
        }
    }.flowOn(Dispatchers.IO)

    fun <T> sendWebSocketMessage(data: T, serializer: KSerializer<T>) {
        try {
            val jsonString = Json.encodeToString(serializer, data)
            sendMessage(jsonString)
            Log.d("WebSocket", "Message sent: $jsonString")
        } catch (e: Exception) {
            Log.e("WebSocket", "Failed to send message: ${e.localizedMessage}")
        }
    }
}