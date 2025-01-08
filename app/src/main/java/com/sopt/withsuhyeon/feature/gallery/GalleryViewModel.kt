package com.sopt.withsuhyeon.feature.gallery

import com.sopt.withsuhyeon.core.base.BaseViewModel
import com.sopt.withsuhyeon.feature.gallery.contract.GalleryContract.GalleryUiState
import com.sopt.withsuhyeon.feature.gallery.contract.GalleryContract.GalleryEvent
import com.sopt.withsuhyeon.feature.gallery.contract.GalleryContract.GallerySideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
) : BaseViewModel<GalleryUiState, GallerySideEffect, GalleryEvent>() {
    override fun createInitialState(): GalleryUiState {
        return GalleryUiState()
    }

    override suspend fun handleEvent(event: GalleryEvent) {
        when(event) {
            else -> {}
        }
    }

}