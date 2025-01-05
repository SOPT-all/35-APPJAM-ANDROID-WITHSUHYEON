package com.sopt.withsuhyeon.data.di

import com.sopt.withsuhyeon.data.service.DummyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): DummyService =
        retrofit.create(DummyService::class.java)
}