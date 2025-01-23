package com.sopt.withsuhyeon.feature.gallery

import android.content.ContentResolver
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.core.util.image.ContentUriRequestBody
import com.sopt.withsuhyeon.data.dto.request.RequestUploadGalleryDto
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
class GalleryUploadViewModel @Inject constructor(
    private val galleryRepository: GalleryRepository
) : ViewModel() {

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _uploadState = MutableStateFlow<Result<Unit>?>(null)
    val uploadState: StateFlow<Result<Unit>?> = _uploadState.asStateFlow()

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
}