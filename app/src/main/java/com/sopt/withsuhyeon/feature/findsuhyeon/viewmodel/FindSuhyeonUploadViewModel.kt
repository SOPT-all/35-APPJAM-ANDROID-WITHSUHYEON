package com.sopt.withsuhyeon.feature.findsuhyeon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.core.util.KeyStorage.MALE
import com.sopt.withsuhyeon.core.util.KeyStorage.MAX_PRICE_STRING
import com.sopt.withsuhyeon.core.util.KeyStorage.SHORT_TEXTFIELD_MAX_LENGTH
import com.sopt.withsuhyeon.core.util.time.currentDateTime
import com.sopt.withsuhyeon.domain.entity.FindSuhyeonPostUploadModel
import com.sopt.withsuhyeon.domain.repository.FindSuhyeonRepository
import com.sopt.withsuhyeon.feature.findsuhyeon.state.FindSuhyeonDetailState
import com.sopt.withsuhyeon.feature.findsuhyeon.state.FindSuhyeonUploadState
import com.sopt.withsuhyeon.feature.findsuhyeon.type.BottomSheetType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class FindSuhyeonUploadViewModel @Inject constructor(
    val findSuhyeonRepository: FindSuhyeonRepository
) : ViewModel() {
    val subLocationList = mutableListOf(
        listOf(
            "강남/역삼/삼성",
            "신사/청담/압구정",
            "서초/교대/사당/동작",
            "잠실/송파/강동",
            "을지로/명동/중구/동대문",
            "서울역/이태원/용산",
            "종로/인사동",
            "홍대/합정/마포/서대문/은평",
            "여의도/영등포역/목동/양천",
            "구로/신도림/금천/관악/신림",
            "김포공항/염창/강서",
            "건대입구/성수/왕십리",
            "성북/강북/노원/도봉/중랑"
        ),
        listOf(
            "해운대/마린시티",
            "벡스코/센텀시티",
            "서초/교대/사당/동작",
            "잠실/송파/강동",
            "을지로/명동/중구/동대문",
            "서울역/이태원/용산",
            "종로/인사동",
            "홍대/합정/마포/서대문/은평",
            "여의도/영등포역/목동/양천",
            "구로/신도림/금천/관악/신림",
            "김포공항/염창/강서",
            "건대입구/성수/왕십리",
            "성북/강북/노원/도봉/중랑"
        )
    )
    val mainLocationList = mutableListOf(
        "전국",
        "서울",
        "부산",
        "제주",
        "인천",
        "강원",
        "경기",
        "경상"
    )

    private val _uploadState = MutableStateFlow(FindSuhyeonUploadState(
        mainLocationList = mainLocationList,
        subLocationList = subLocationList
    ))
    val uploadState: StateFlow<FindSuhyeonUploadState> = _uploadState

    private val _detailState = MutableStateFlow(FindSuhyeonDetailState())
    val detailState: StateFlow<FindSuhyeonDetailState> = _detailState

    fun postFindSuhyeonUpload(){
        viewModelScope.launch {
            _uploadState.value.run {
                findSuhyeonRepository.postFindSuhyeonUpload(
                    request = FindSuhyeonPostUploadModel(
                        region = selectedSubLocation.orEmpty(),
                        gender = selectedGender == MALE,
                        age = selectedAge.orEmpty(),
                        date = selectedDate ?: currentDateTime,
                        price = selectedPrice ?: 0,
                        requests = selectedRequirementsList,
                        title = _detailState.value.titleValue,
                        content = _detailState.value.findSuhyeonContentValue
                    )
                )
            }
        }
    }
    fun updateGender(selectedGender: String) {
        _uploadState.update {
            it.copy(
                selectedGender = selectedGender,
                isSelectedGender = selectedGender.isNotEmpty()
            )
        }
    }

    fun updateAge(selectedAge: String) {
        _uploadState.update {
            it.copy(
                selectedAge = selectedAge,
                isSelectedAge = selectedAge.isNotEmpty()
            )
        }
    }

    fun updateRequirements(selectedRequirements: List<String>) {
        _uploadState.update {
            it.copy(
                selectedRequirementsList = selectedRequirements,
                isSelectedRequirements = true
            )
        }
    }

    fun updateLocation(location: Pair<String?, String?>) {
        _uploadState.update {
            it.copy(
                selectedLocation = location,
                selectedMainLocation = location.first,
                selectedSubLocation = location.second,
                isSelectedLocation = !location.second.isNullOrEmpty()
            )
        }
    }

    fun updateIsPriceTextFieldFocused(isPriceTextFieldFocused: Boolean) {
        _uploadState.update { it.copy(isPriceTextFieldFocused = isPriceTextFieldFocused) }
    }

    fun updateDate(selectedDate: LocalDateTime, selectedDateString: String) {
        _uploadState.update {
            it.copy(
                selectedDate = selectedDate,
                selectedDateString = selectedDateString,
                isSelectedDate = selectedDateString.isNotEmpty()
            )
        }
    }

    fun updatePrice(input: String, errorMessage: String?) {
        val isEmpty = input.isEmpty()
        val isBelowMax = input.length < MAX_PRICE_STRING.length ||
                (input.length == MAX_PRICE_STRING.length && input <= MAX_PRICE_STRING)

        val isPriceValid = !isEmpty && isBelowMax

        _uploadState.update {
            it.copy(
                priceTextValue = input,
                isPriceValid = isPriceValid,
                priceErrorMessage = if (!isEmpty && !isBelowMax) errorMessage.orEmpty() else "",
                selectedPrice = if (isPriceValid) input.toInt() else _uploadState.value.selectedPrice,
                isSelectedPrice = isPriceValid,
                priceButtonEnabled = isPriceValid
            )
        }
    }

    fun updateTitle(
        value: String,
        maxLength: Int = SHORT_TEXTFIELD_MAX_LENGTH,
        errorMessage: String
    ) {
        _detailState.update {
            val isValid = value.length <= maxLength
            it.copy(
                titleValue = value,
                isTitleValid = isValid,
                titleErrorMessage = if (isValid) "" else errorMessage
            )
        }
    }

    fun updateContent(value: String) {
        _detailState.update { it.copy(findSuhyeonContentValue = value) }
    }

    fun updateIsTitleValid(isValid: Boolean) {
        _detailState.update { it.copy(isTitleValid = isValid) }
    }

    fun updateErrorMessage(message: String) {
        _detailState.update { it.copy(titleErrorMessage = message) }
    }

    fun updateProgress(currentStep: Int, totalSteps: Int) {
        val progress = (currentStep.toFloat() / totalSteps)
        _uploadState.update { it.copy(progress = progress) }
        _detailState.update { it.copy(progress = progress) }
    }

    fun toggleBottomSheet(type: BottomSheetType) {
        _uploadState.update {
            when (type) {
                BottomSheetType.AGE -> it.copy(isAgeBottomSheetVisible = !it.isAgeBottomSheetVisible)
                BottomSheetType.REQUIREMENTS -> it.copy(isRequirementsBottomSheetVisible = !it.isRequirementsBottomSheetVisible)
                BottomSheetType.LOCATION -> it.copy(isLocationBottomSheetVisible = !it.isLocationBottomSheetVisible)
                BottomSheetType.DATE -> it.copy(isDateTimePickerBottomSheetVisible = !it.isDateTimePickerBottomSheetVisible)
            }
        }
    }

    fun hideBottomSheet(type: BottomSheetType) {
        _uploadState.update {
            when (type) {
                BottomSheetType.AGE -> it.copy(isAgeBottomSheetVisible = false)
                BottomSheetType.REQUIREMENTS -> it.copy(isRequirementsBottomSheetVisible = false)
                BottomSheetType.LOCATION -> it.copy(isLocationBottomSheetVisible = false)
                BottomSheetType.DATE -> it.copy(isDateTimePickerBottomSheetVisible = false)
            }
        }
    }
}