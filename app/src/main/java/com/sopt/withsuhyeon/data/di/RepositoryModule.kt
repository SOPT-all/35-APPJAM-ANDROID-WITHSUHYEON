package com.sopt.withsuhyeon.data.di

import com.sopt.withsuhyeon.data.repositoryimpl.BlockUserRepositoryImpl
import com.sopt.withsuhyeon.data.repositoryimpl.ChatRepositoryImpl
import com.sopt.withsuhyeon.data.repositoryimpl.DummyRepositoryImpl
import com.sopt.withsuhyeon.data.repositoryimpl.FindSuhyeonRepositoryImpl
import com.sopt.withsuhyeon.data.repositoryimpl.GalleryRepositoryImpl
import com.sopt.withsuhyeon.data.repositoryimpl.MyPageRepositoryImpl
import com.sopt.withsuhyeon.data.repositoryimpl.SignUpRepositoryImpl
import com.sopt.withsuhyeon.data.repositoryimpl.HomeRepositoryImpl
import com.sopt.withsuhyeon.data.repositoryimpl.LoginRepositoryImpl
import com.sopt.withsuhyeon.data.repositoryimpl.UserRepositoryImpl
import com.sopt.withsuhyeon.domain.repository.BlockUserRepository
import com.sopt.withsuhyeon.domain.repository.ChatRepository
import com.sopt.withsuhyeon.domain.repository.DummyRepository
import com.sopt.withsuhyeon.domain.repository.FindSuhyeonRepository
import com.sopt.withsuhyeon.domain.repository.GalleryRepository
import com.sopt.withsuhyeon.domain.repository.MyPageRepository
import com.sopt.withsuhyeon.domain.repository.SignUpRepository
import com.sopt.withsuhyeon.domain.repository.HomeRepository
import com.sopt.withsuhyeon.domain.repository.LoginRepository
import com.sopt.withsuhyeon.domain.repository.UserRepository
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

    @Binds
    @Singleton
    abstract fun bindsMyPageRepository(myPageRepositoryImpl: MyPageRepositoryImpl): MyPageRepository

    @Binds
    @Singleton
    abstract fun bindsSignUpRepository(signUpRepositoryImpl: SignUpRepositoryImpl): SignUpRepository

    @Binds
    @Singleton
    abstract fun bindsHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    @Singleton
    abstract fun bindsFindSuhyeonRepository(findSuhyeonRepositoryImpl: FindSuhyeonRepositoryImpl): FindSuhyeonRepository

    @Binds
    @Singleton
    abstract fun bindsLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    abstract fun bindsBlockUserRepository(blockUserRepositoryImpl: BlockUserRepositoryImpl): BlockUserRepository

    @Binds
    @Singleton
    abstract fun bindsUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindsChatRepository(chatRepositoryImpl: ChatRepositoryImpl): ChatRepository
}