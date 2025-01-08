package com.sopt.withsuhyeon.feature.dummy

import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.base.BaseViewModel
import com.sopt.withsuhyeon.domain.repository.DummyRepository
import com.sopt.withsuhyeon.feature.dummy.contract.DummyContract.DummyUiState
import com.sopt.withsuhyeon.feature.dummy.contract.DummyContract.DummySideEffect
import com.sopt.withsuhyeon.feature.dummy.contract.DummyContract.DummyEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DummyViewModel @Inject constructor(
    private val dummyRepository: DummyRepository
) : BaseViewModel<DummyUiState, DummySideEffect, DummyEvent>() {

    override fun createInitialState(): DummyUiState {
        return DummyUiState()
    }

    override suspend fun handleEvent(event: DummyEvent) {
        when (event) {
            is DummyEvent.FetchReviews -> {
                setState { copy(isLoading = true) }
                fetchReviews(event.productId)
            }

            is DummyEvent.FetchReviewsSuccess -> {
                setState { copy(reviews = event.reviews, isLoading = false) }
            }

            is DummyEvent.FetchReviewsFailure -> {
                setState { copy(isLoading = false) }
                setSideEffect {
                    DummySideEffect.ShowSnackbar(R.string.error_fetch_reviews)
                }
            }

            is DummyEvent.OnToggleFavorite -> {
                setState { copy(isFavorite = !isFavorite) }
                if (uiState.value.isFavorite) {
                    setSideEffect {
                        DummySideEffect.ShowSnackbar(R.string.goods_snackbar_message_favorite)
                    }
                }
            }

            is DummyEvent.NavigateToWishlist -> {
                setSideEffect {
                    DummySideEffect.NavigateToWishlist
                }
            }

            is DummyEvent.FetchUserDetails -> {
                setState { copy(isLoading = true) }
                fetchUserDetails(event.userId)
            }

            is DummyEvent.FetchUserDetailsSuccess -> {
                setState { copy(user = event.user, isLoading = false) }
            }

            is DummyEvent.FetchUserDetailsFailure -> {
                setState { copy(isLoading = false) }
                setSideEffect {
                    DummySideEffect.ShowSnackbar(R.string.error_fetch_user_details)
                }
            }
        }
    }

    private fun fetchReviews(productId: Number) {
        viewModelScope.launch {
            dummyRepository.getProductReviews(productId)
                .onSuccess { reviews ->
                    setEvent(DummyEvent.FetchReviewsSuccess(reviews))
                }
                .onFailure {
                    setEvent(DummyEvent.FetchReviewsFailure)
                }
        }
    }

    private fun fetchUserDetails(userId: Int) {
        viewModelScope.launch {
            dummyRepository.getUserDetails(userId)
                .onSuccess { user ->
                    setEvent(DummyEvent.FetchUserDetailsSuccess(user))
                }
                .onFailure {
                    setEvent(DummyEvent.FetchUserDetailsFailure)
                }
        }
    }
}