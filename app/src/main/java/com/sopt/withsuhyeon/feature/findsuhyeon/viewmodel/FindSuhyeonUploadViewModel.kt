package com.sopt.withsuhyeon.feature.findsuhyeon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.core.util.KeyStorage.MALE
import com.sopt.withsuhyeon.core.util.KeyStorage.MAX_PRICE_STRING
import com.sopt.withsuhyeon.core.util.KeyStorage.SHORT_TEXTFIELD_MAX_LENGTH
import com.sopt.withsuhyeon.core.util.time.currentDateTime
import com.sopt.withsuhyeon.domain.entity.FindSuhyeonPostUploadModel
import com.sopt.withsuhyeon.domain.entity.LocationListModel
import com.sopt.withsuhyeon.domain.repository.FindSuhyeonRepository
import com.sopt.withsuhyeon.feature.findsuhyeon.state.FindSuhyeonDetailState
import com.sopt.withsuhyeon.feature.findsuhyeon.state.FindSuhyeonUploadState
import com.sopt.withsuhyeon.feature.findsuhyeon.type.BottomSheetType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class FindSuhyeonUploadViewModel @Inject constructor(
    val findSuhyeonRepository: FindSuhyeonRepository
) : ViewModel() {
    private val _uploadState = MutableStateFlow(FindSuhyeonUploadState())
    val uploadState: StateFlow<FindSuhyeonUploadState> = _uploadState

    private val _detailState = MutableStateFlow(FindSuhyeonDetailState())
    val detailState: StateFlow<FindSuhyeonDetailState> = _detailState

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

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
    fun showLocationBottomSheet() {
        viewModelScope.launch {
            findSuhyeonRepository.getRegionList().onSuccess { regionList ->
                _uploadState.update { current ->
                    current.copy(
                        regionList = regionList,
                        selectedMainLocation = findLocationBySubLocation(
                            _uploadState.value.selectedSubLocation.orEmpty(),
                            regionList.regions
                        ),
                        mainLocationList = regionList.regions.map { it.location },
                        subLocationList = regionList.regions.map { it.subLocations }
                    )
                }
            }.onFailure { error ->
                _errorMessage.update { error.message }
            }

            _uploadState.update { current ->
                current.copy(isLocationBottomSheetVisible = true)
            }
        }
    }
    private fun findLocationBySubLocation(
        subLocation: String,
        regions: List<LocationListModel>
    ): String? {
        for (region in regions) {
            if (subLocation in region.subLocations) {
                return region.location
            }
        }
        return null
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