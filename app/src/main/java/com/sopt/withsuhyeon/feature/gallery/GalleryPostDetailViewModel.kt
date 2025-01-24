package com.sopt.withsuhyeon.feature.gallery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.domain.entity.GalleryPostDetailModel
import com.sopt.withsuhyeon.domain.repository.GalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryPostDetailViewModel @Inject constructor(
    private val galleryRepository: GalleryRepository
) : ViewModel() {

    private val _galleryPostDetail = MutableStateFlow(
        GalleryPostDetailModel(
            imageUrl = "",
            category = "",
            title = "",
            profileImage = "",
            nickname = "",
            createdAt = "",
            content = "",
            owner = true
        )
    )
    val galleryPostDetail: StateFlow<GalleryPostDetailModel> = _galleryPostDetail.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun getGalleryPostDetail(galleryId: Long) {
        viewModelScope.launch {
            galleryRepository.getGalleryPostDetail(galleryId)
                .onSuccess { data ->
                    _galleryPostDetail.value = GalleryPostDetailModel(
                        imageUrl = data.imageUrl,
                        category = data.category,
                        title = data.title,
                        profileImage = data.profileImage,
                        nickname = data.nickname,
                        createdAt = data.createdAt,
                        content = data.content,
                        owner = data.owner
                    )
                }
                .onFailure { error ->
                    _errorMessage.update { error.message }
                }
        }
    }

    fun deleteGalleryPost(galleryId: Long) {
        viewModelScope.launch {
            galleryRepository.deleteGalleryPost(galleryId)
                .onSuccess {
                    Log.d("갤러리 삭제", "성공")
                }
                .onFailure {
                    Log.d("갤러리 삭제", "실패")
                }
        }
    }
}