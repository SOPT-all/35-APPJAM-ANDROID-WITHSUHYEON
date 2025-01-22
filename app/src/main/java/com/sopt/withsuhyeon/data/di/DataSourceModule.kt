package com.sopt.withsuhyeon.data.di

import com.sopt.withsuhyeon.data.datasource.DummyDataSource
import com.sopt.withsuhyeon.data.datasource.FindSuhyeonDataSource
import com.sopt.withsuhyeon.data.datasource.GalleryDataSource
import com.sopt.withsuhyeon.data.datasource.HomeDataSource
import com.sopt.withsuhyeon.data.datasourceimpl.DummyDataSourceImpl
import com.sopt.withsuhyeon.data.datasourceimpl.FindSuhyeonDataSourceImpl
import com.sopt.withsuhyeon.data.datasourceimpl.GalleryDataSourceImpl
import com.sopt.withsuhyeon.data.datasourceimpl.HomeDataSourceImpl
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
    abstract fun bindsHomeDataSource(homeDataSourceImpl: HomeDataSourceImpl): HomeDataSource

    @Binds
    @Singleton
    abstract fun bindsFindSuhyeonDataSource(findSuhyeonDataSourceImpl: FindSuhyeonDataSourceImpl): FindSuhyeonDataSource
}