package com.sopt.withsuhyeon.data.repositoryimpl

import android.util.Log
import coil.network.HttpException
import com.sopt.withsuhyeon.BuildConfig.WEB_SOCKET_URL
import com.sopt.withsuhyeon.data.service.UserService
import com.sopt.withsuhyeon.data.socket.WebSocketClient
import com.sopt.withsuhyeon.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
) : UserRepository {
    val webSocketClient = WebSocketClient.getInstance()
    override suspend fun connectWithUserId(): Result<Unit> =
        runCatching {
            /*val response = */userService.getUserId()
            /*Log.e("impl", "$response")
            response.result ?: throw Exception("Response data is null")
            webSocketClient.connect(WEB_SOCKET_URL)*/
        }.mapCatching {
            val response = it.result?.userId
            webSocketClient.connect(WEB_SOCKET_URL+"/chat?userId=$response")
            //response.result ?: throw Exception("Response data is null")
        }.recoverCatching { throwable ->
            Log.e("ss", throwable.message.toString())
            if(throwable is HttpException){
                Log.e("ss", throwable.message.toString())
            }
        }
}