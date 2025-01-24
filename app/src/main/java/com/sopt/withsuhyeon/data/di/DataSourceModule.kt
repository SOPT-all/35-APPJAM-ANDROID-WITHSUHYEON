package com.sopt.withsuhyeon.data.di

import com.sopt.withsuhyeon.data.datasource.DummyDataSource
import com.sopt.withsuhyeon.data.datasource.FindSuhyeonDataSource
import com.sopt.withsuhyeon.data.datasource.GalleryDataSource
import com.sopt.withsuhyeon.data.datasource.SignUpDataSource
import com.sopt.withsuhyeon.data.datasource.MyPageDataSource
import com.sopt.withsuhyeon.data.datasource.HomeDataSource
import com.sopt.withsuhyeon.data.datasource.LoginDataSource
import com.sopt.withsuhyeon.data.datasourceimpl.DummyDataSourceImpl
import com.sopt.withsuhyeon.data.datasourceimpl.FindSuhyeonDataSourceImpl
import com.sopt.withsuhyeon.data.datasourceimpl.GalleryDataSourceImpl
import com.sopt.withsuhyeon.data.datasourceimpl.SignUpDataSourceImpl
import com.sopt.withsuhyeon.data.datasourceimpl.MyPageDataSourceImpl
import com.sopt.withsuhyeon.data.datasourceimpl.HomeDataSourceImpl
import com.sopt.withsuhyeon.data.datasourceimpl.LoginDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsDummyDataSource(dummyDataSourceImpl: DummyDataSourceImpl): DummyDataSource

    @Binds
    @Singleton
    abstract fun bindsGalleryDataSource(galleryDataSourceImpl: GalleryDataSourceImpl): GalleryDataSource

    @Binds
    @Singleton
    abstract fun bindsMyPageDataSource(myPageDataSourceImpl: MyPageDataSourceImpl): MyPageDataSource

    @Binds
    @Singleton
    abstract fun bindsHomeDataSource(homeDataSourceImpl: HomeDataSourceImpl): HomeDataSource

    @Binds
    @Singleton
    abstract fun bindsFindSuhyeonDataSource(findSuhyeonDataSourceImpl: FindSuhyeonDataSourceImpl): FindSuhyeonDataSource

    @Binds
    @Singleton
    abstract fun bindsSignUpDataSource(signUpDataSourceImpl: SignUpDataSourceImpl): SignUpDataSource

    @Binds
    @Singleton
    abstract fun bindsLoginDataSource(loginDataSourceImpl: LoginDataSourceImpl): LoginDataSource
}