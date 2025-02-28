package com.sopt.withsuhyeon.data.di

import com.sopt.withsuhyeon.BuildConfig
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
    fun provideWebSocketUrl(): String {
        return BuildConfig.WEB_SOCKET_URL
    }
}