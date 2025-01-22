package com.sopt.withsuhyeon.feature.findsuhyeon.viewmodel

import androidx.lifecycle.ViewModel
import com.sopt.withsuhyeon.core.type.MediumChipType
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_20_TO_24
import com.sopt.withsuhyeon.domain.entity.PostDetailInfoModel
import com.sopt.withsuhyeon.feature.findsuhyeon.state.FindSuhyeonPostState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FindSuhyeonPostViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(FindSuhyeonPostState())
    val state: StateFlow<FindSuhyeonPostState> = _state

    fun loadPostData() {
        _state.update {
            it.copy(
                postTitle = "강남역 수현이 구해요",
                postDescription = "강남역에서 사진 찍을 수현이 있나요? 강남역에서 사진 찍을 수현이 있나요? 강남역에서 사진 찍을 수현이 있나요?",
                profileImage = "https://via.placeholder.com/150",
                userName = "작심이",
                date = "1월 12일",
                detailInfo = PostDetailInfoModel(
                    region = "강남/역삼/삼성",
                    gender = false,
                    age = AGE_20_TO_24,
                    date = "1월 25일 (토) 오후 2:00",
                    price = 5000,
                    requests = listOf(
                        MediumChipType.CATEGORY_PHOTO,
                        MediumChipType.CATEGORY_VIDEO_CALL,
                        MediumChipType.CATEGORY_PHONE_CALL,
                    )
                )
            )
        }
    }

    fun toggleDeleteBottomSheet(isVisible: Boolean) {
        _state.update {
            it.copy(isDeleteBottomSheetVisible = isVisible)
        }
    }

    fun toggleDeleteAlertModal(isVisible: Boolean) {
        _state.update {
            it.copy(isDeleteAlertModalVisible = isVisible)
        }
    }
}