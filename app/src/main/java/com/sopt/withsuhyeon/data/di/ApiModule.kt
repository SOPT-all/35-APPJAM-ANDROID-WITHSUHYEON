package com.sopt.withsuhyeon.data.di

import com.sopt.withsuhyeon.data.service.DummyService
import com.sopt.withsuhyeon.data.service.FindSuhyeonService
import com.sopt.withsuhyeon.data.service.GalleryService
import com.sopt.withsuhyeon.data.service.SignUpService
import com.sopt.withsuhyeon.data.service.MyPageService
import com.sopt.withsuhyeon.data.service.HomeService
import com.sopt.withsuhyeon.data.service.MyPageService
import com.sopt.withsuhyeon.data.service.LoginService
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
    fun providesDummyService(retrofit: Retrofit): DummyService =
        retrofit.create(DummyService::class.java)

    @Provides
    @Singleton
    fun providesGalleryService(retrofit: Retrofit): GalleryService =
        retrofit.create(GalleryService::class.java)

    @Provides
    @Singleton
    fun providesMyPageService(retrofit: Retrofit): MyPageService =
        retrofit.create(MyPageService::class.java)

    @Provides
    @Singleton
    fun provideSignUpService(retrofit: Retrofit): SignUpService =
        retrofit.create(SignUpService::class.java)

    @Provides
    @Singleton
    fun providesHomeService(retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)

    @Provides
    @Singleton
    fun providesFidnSuhyeonService(retrofit: Retrofit): FindSuhyeonService =
        retrofit.create(FindSuhyeonService::class.java)

    @Provides
    @Singleton
    fun providesLoginService(retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)
}