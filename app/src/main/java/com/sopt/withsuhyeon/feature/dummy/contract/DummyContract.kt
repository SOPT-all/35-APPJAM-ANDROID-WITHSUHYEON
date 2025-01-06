package com.sopt.withsuhyeon.feature.dummy.contract

import com.sopt.withsuhyeon.core.base.UiEvent
import com.sopt.withsuhyeon.core.base.UiSideEffect
import com.sopt.withsuhyeon.core.base.UiState
import com.sopt.withsuhyeon.domain.entity.DummyModel
import com.sopt.withsuhyeon.domain.entity.UserModel

class DummyContract {
    data class DummyUiState(
        val reviews: List<DummyModel> = emptyList(),
        val isFavorite: Boolean = false,
        val isLoading: Boolean = false,
        val user: UserModel? = null
    ) : UiState

    sealed interface DummySideEffect : UiSideEffect {
        data class ShowSnackbar(val messageResId: Int) : DummySideEffect
        data object NavigateToWishlist : DummySideEffect
    }

    sealed interface DummyEvent : UiEvent {
        data class FetchReviews(val productId: Number) : DummyEvent
        data class FetchReviewsSuccess(val reviews: List<DummyModel>) : DummyEvent
        data object FetchReviewsFailure : DummyEvent

        data object OnToggleFavorite : DummyEvent
        data object NavigateToWishlist : DummyEvent

        data class FetchUserDetails(val userId: Int) : DummyEvent
        data class FetchUserDetailsSuccess(val user: UserModel) : DummyEvent
        data object FetchUserDetailsFailure : DummyEvent
    }
}