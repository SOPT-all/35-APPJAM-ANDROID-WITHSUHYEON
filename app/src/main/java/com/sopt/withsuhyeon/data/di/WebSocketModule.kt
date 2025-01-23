package com.sopt.withsuhyeon.data.di

import com.sopt.withsuhyeon.BuildConfig
import com.sopt.withsuhyeon.data.socket.WebSocketClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WebSocketModule {
    @Provides
    @Singleton
    fun provideWebSocketClient(): WebSocketClient {
        return WebSocketClient.getInstance()
    }

    @Provides
    @Singleton
    fun provideWebSocketUrl(): String {
        return BuildConfig.WEB_SOCKET_URL
    }
}