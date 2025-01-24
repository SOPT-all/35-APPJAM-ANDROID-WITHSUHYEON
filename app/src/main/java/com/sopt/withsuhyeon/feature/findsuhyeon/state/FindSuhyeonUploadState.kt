package com.sopt.withsuhyeon.feature.findsuhyeon.state

import com.sopt.withsuhyeon.domain.entity.RegionListModel
import kotlinx.datetime.LocalDateTime

data class FindSuhyeonUploadState(
    val subLocationList: List<List<String>> = mutableListOf(emptyList()),
    val mainLocationList: List<String> = mutableListOf(),

    val selectedGender: String? = null,
    val selectedAge: String? = null,
    val selectedRequirementsList: List<String> = emptyList(),
    val selectedLocation: Pair<String?, String?> = Pair(null, null),
    val selectedMainLocation: String? = null,
    val selectedSubLocation: String? = null,
    val selectedDate: LocalDateTime? = null,
    val selectedDateString: String? = null,
    val selectedPrice: Int? = null,

    val isSelectedGender: Boolean = false,
    val isSelectedAge: Boolean = false,
    val isSelectedRequirements: Boolean = false,
    val isSelectedLocation: Boolean = false,
    val isSelectedDate: Boolean = false,
    val isSelectedPrice: Boolean = false,

    val isPriceTextFieldFocused: Boolean = false,

    val priceTextValue: String = "",
    val isPriceValid: Boolean = false,
    val priceErrorMessage: String = "",
    val priceButtonEnabled: Boolean = false,

    val isAgeBottomSheetVisible: Boolean = false,
    val isRequirementsBottomSheetVisible: Boolean = false,
    val isLocationBottomSheetVisible: Boolean = false,
    val isDateTimePickerBottomSheetVisible: Boolean = false,

    val isDeleteAlertModalVisible: Boolean = false,

    val progress: Float = 1f / 7,

    val isComplete: Boolean = isSelectedGender && isSelectedAge && isSelectedRequirements && isSelectedLocation && isSelectedPrice,

    val regionList: RegionListModel = RegionListModel(
        regions = emptyList()
    )
)
