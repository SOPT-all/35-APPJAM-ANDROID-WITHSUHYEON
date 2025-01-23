package com.sopt.withsuhyeon.feature.findsuhyeon.viewmodel

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.core.type.MediumChipType
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_20_TO_24
import com.sopt.withsuhyeon.data.service.FindSuhyeonService
import com.sopt.withsuhyeon.domain.repository.FindSuhyeonRepository
import com.sopt.withsuhyeon.feature.findsuhyeon.state.FindSuhyeonPostState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindSuhyeonPostViewModel @Inject constructor(
    val findSuhyeonRepository: FindSuhyeonRepository
) : ViewModel() {
    private val _state = MutableStateFlow(FindSuhyeonPostState())
    val state: StateFlow<FindSuhyeonPostState> = _state

    fun getFindSuhyeonPostDetail(postId: Long) {
        viewModelScope.launch {
            findSuhyeonRepository.getFindSuhyeonPostDetail(postId)
                .onSuccess { postDetailData ->
                    _state.update { current ->
                        current.copy(
                            postDetailData = postDetailData
                        )

                    }
                }
        }
    }
    fun deleteFindSuhyeonPost(postId: Long) {
        viewModelScope.launch {
            findSuhyeonRepository.deleteFindSuhyeonPost(postId)
        }
    }
    fun toggleDeleteBottomSheet() {
        _state.update {
            it.copy(isDeleteBottomSheetVisible = !_state.value.isDeleteBottomSheetVisible)
        }
    }

    fun toggleDeleteAlertModal(isVisible: Boolean) {
        _state.update {
            it.copy(isDeleteAlertModalVisible = isVisible)
        }
    }
}