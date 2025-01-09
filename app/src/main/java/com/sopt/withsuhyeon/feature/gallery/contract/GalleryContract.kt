package com.sopt.withsuhyeon.feature.gallery.contract

import com.sopt.withsuhyeon.core.base.UiEvent
import com.sopt.withsuhyeon.core.base.UiSideEffect
import com.sopt.withsuhyeon.core.base.UiState

class GalleryContract {
    data class GalleryUiState(
        val isLoading: Boolean = false,
    ) : UiState

    sealed interface GallerySideEffect : UiSideEffect {
    }

    sealed interface GalleryEvent : UiEvent {
    }
}