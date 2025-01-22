package com.sopt.withsuhyeon.feature.findsuhyeon.viewmodel

import androidx.lifecycle.ViewModel
import com.sopt.withsuhyeon.feature.findsuhyeon.state.FindSuhyeonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FindSuhyeonViewModel @Inject constructor(
) : ViewModel() {
    private val _state = MutableStateFlow(FindSuhyeonState())
    val state: StateFlow<FindSuhyeonState> = _state

    fun toggleLocationBottomSheet() {
        _state.value = _state.value.copy(
            isLocationBottomSheetVisible = !_state.value.isLocationBottomSheetVisible
        )
    }

    fun selectLocation(location: Pair<String?, String?>) {
        _state.value = _state.value.copy(
            selectedLocation = location,
            selectedMainLocation = location.first,
            selectedSubLocation = location.second,
            isLocationBottomSheetVisible = false
        )
    }

    fun selectDate(date: String) {
        _state.value = _state.value.copy(selectedDate = date)
    }
}