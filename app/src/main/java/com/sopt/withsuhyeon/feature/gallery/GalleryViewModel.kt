package com.sopt.withsuhyeon.feature.gallery

import android.content.ContentResolver
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.core.util.image.ContentUriRequestBody
import com.sopt.withsuhyeon.data.dto.request.RequestUploadGalleryDto
import com.sopt.withsuhyeon.domain.entity.Category
import com.sopt.withsuhyeon.domain.entity.Gallery
import com.sopt.withsuhyeon.domain.entity.GalleryPostDetailModel
import com.sopt.withsuhyeon.domain.repository.GalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val galleryRepository: GalleryRepository
) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    private val _galleries = MutableStateFlow<List<Gallery>>(emptyList())
    val galleries: StateFlow<List<Gallery>> = _galleries.asStateFlow()

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _uploadState = MutableStateFlow<Result<Unit>?>(null)
    val uploadState: StateFlow<Result<Unit>?> = _uploadState.asStateFlow()

    private val _galleryPostDetail = MutableStateFlow(
        GalleryPostDetailModel(
            imageUrl = "",
            category = "",
            title = "",
            profileImage = "",
            nickname = "",
            createdAt = "",
            content = ""
        )
    )
    val galleryPostDetail: StateFlow<GalleryPostDetailModel> = _galleryPostDetail.asStateFlow()

    init {
        getGalleryCategories()
    }

    fun getGalleryCategories() {
        viewModelScope.launch {
            galleryRepository.getGalleryCategories()
                .onSuccess { categories ->
                    _categories.update { categories }
                }
                .onFailure { error ->
                    _errorMessage.update { error.message }
                }
        }
    }

    fun getGalleryTotal(category: String) {
        viewModelScope.launch {
            galleryRepository.getGalleryTotal(category)
                .onSuccess { galleries ->
                    _galleries.update { galleries }
                }
                .onFailure { error ->
                    _errorMessage.update { error.message }
                }
        }
    }

    fun uploadGallery(
        imageUri: Uri,
        request: RequestUploadGalleryDto,
        contentResolver: ContentResolver
    ) {
        viewModelScope.launch {
            try {
                val imagePart = ContentUriRequestBody(contentResolver, imageUri).toMultiPartData("image")

                val jsonBody = Json.encodeToString(request)
                val requestBody = jsonBody.toRequestBody("application/json".toMediaType())

                galleryRepository.uploadGallery(imagePart, requestBody)
                    .onSuccess {
                        _uploadState.update { Result.success(Unit) }
                    }
                    .onFailure { error ->
                        _uploadState.update { Result.failure(error) }
                        _errorMessage.update { error.message }
                    }
            } catch (e: Exception) {
                _errorMessage.update { e.message ?: "업로드 중 오류 발생" }
            }
        }
    }

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
                        content = data.content
                    )
                }
                .onFailure { error ->
                    _errorMessage.update { error.message }
                }
        }
    }
}