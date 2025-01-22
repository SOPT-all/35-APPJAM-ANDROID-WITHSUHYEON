package com.sopt.withsuhyeon.feature.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val galleryRepository: GalleryRepository
) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    private val _galleries = MutableStateFlow<List<Gallery>>(emptyList())
    val galleries: StateFlow<List<Gallery>> = _galleries.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _galleryPostDetail = MutableStateFlow<GalleryPostDetailModel?>(null)
    val galleryPostDetail: StateFlow<GalleryPostDetailModel?> = _galleryPostDetail.asStateFlow()

    private val _selectedCategory = MutableStateFlow("전체")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

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
            val result = if (category == "전체") {
                galleryRepository.getAllGalleries()
            } else {
                galleryRepository.getGalleryTotal(category)
            }

            result
                .onSuccess { galleries ->
                    _galleries.update { galleries }
                }
                .onFailure { error ->
                    _errorMessage.update { error.message }
                }
        }
    }

    fun setSelectedCategory(category: String) {
        _selectedCategory.value = category
    }
}