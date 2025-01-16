package com.sopt.withsuhyeon.feature.gallery

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
) : ViewModel() {

    private val _categories = MutableStateFlow(
        listOf("전체", "바다", "학교", "파티룸", "엠티", "수현", "이랑", "합숙")
    )
    val categories: StateFlow<List<String>> = _categories.asStateFlow()

    private val _items = MutableStateFlow(
        listOf(
            "술자리 사진 모음.zip" to "https://via.placeholder.com/150",
            "술자리 사진 모음.zip" to "https://via.placeholder.com/150",
            "술자리 사진 모음.zip" to "https://via.placeholder.com/150",
            "술자리 사진 모음.zip" to "https://via.placeholder.com/150",
            "술자리 사진 모음.zip" to "https://via.placeholder.com/150",
            "술자리 사진 모음.zip" to "https://via.placeholder.com/150",
            "술자리 사진 모음.zip" to "https://via.placeholder.com/150",
            "술자리 사진 모음.zip" to "https://via.placeholder.com/150"
        )
    )
    val items: StateFlow<List<Pair<String, String>>> = _items.asStateFlow()
}