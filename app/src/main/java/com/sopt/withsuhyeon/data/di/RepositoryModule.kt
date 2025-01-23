package com.sopt.withsuhyeon.data.di

import com.sopt.withsuhyeon.data.repositoryimpl.DummyRepositoryImpl
import com.sopt.withsuhyeon.data.repositoryimpl.GalleryRepositoryImpl
import com.sopt.withsuhyeon.domain.repository.DummyRepository
import com.sopt.withsuhyeon.domain.repository.GalleryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsDummyRepository(dummyRepositoryImpl: DummyRepositoryImpl): DummyRepository

    @Binds
    @Singleton
    abstract fun bindsGalleryRepository(galleryRepositoryImpl: GalleryRepositoryImpl): GalleryRepository
}