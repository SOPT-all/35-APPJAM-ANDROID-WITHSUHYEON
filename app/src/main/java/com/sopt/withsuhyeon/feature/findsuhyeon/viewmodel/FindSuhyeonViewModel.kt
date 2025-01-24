package com.sopt.withsuhyeon.feature.findsuhyeon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.domain.entity.LocationListModel
import com.sopt.withsuhyeon.domain.repository.FindSuhyeonRepository
import com.sopt.withsuhyeon.feature.findsuhyeon.state.FindSuhyeonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindSuhyeonViewModel @Inject constructor(
    private val findSuhyeonRepository: FindSuhyeonRepository
) : ViewModel() {
    private val _state = MutableStateFlow(FindSuhyeonState())
    val state: StateFlow<FindSuhyeonState> = _state

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    fun getFindSuhyeonAllPost(regionType: String, date: String) {
        viewModelScope.launch {
            findSuhyeonRepository.getFindSuhyeonAllPost(regionType, date)
                .onSuccess { findSuhyeonData ->
                    _state.update {
                        it.copy(
                            findSuhyeonData = findSuhyeonData,
                            selectedSubLocation = findSuhyeonData.region,
                            selectedDate = date
                        )
                    }
                }
                .onFailure { error ->
                    _errorMessage.update { error.message }
                }
        }
    }
    fun refreshFindSuhyeonAllPost(regionType: String, date: String) {
        viewModelScope.launch {
            _state.update { it.copy(isRefreshing = true) }
            findSuhyeonRepository.getFindSuhyeonAllPost(regionType, date)
                .onSuccess { findSuhyeonData ->
                    _state.update {
                        it.copy(
                            findSuhyeonData = findSuhyeonData,
                            selectedSubLocation = findSuhyeonData.region,
                            selectedDate = date
                        )
                    }
                    _state.update { it.copy(isRefreshing = false) }
                }
                .onFailure { error ->
                    _errorMessage.update { error.message }
                    _state.update { it.copy(isRefreshing = false) }
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

    fun showLocationBottomSheet() {
        viewModelScope.launch {
                findSuhyeonRepository.getRegionList().onSuccess { regionList ->
                    _state.update { current ->
                        current.copy(
                            regionList = regionList,
                            selectedMainLocation = findLocationBySubLocation(
                                _state.value.selectedSubLocation.orEmpty(),
                                regionList.regions
                            ),
                            mainLocationList = regionList.regions.map { it.location },
                            subLocationList = regionList.regions.map { it.subLocations }
                        )
                    }
                }.onFailure { error ->
                    _errorMessage.update { error.message }
                }

            _state.update { current ->
                current.copy(isLocationBottomSheetVisible = true)
            }
        }
    }
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
        )
        getFindSuhyeonAllPost(
            location.second.orEmpty(),
            _state.value.selectedDate
        )
    }
    fun selectMainLocation(location: String?) {
        _state.value = _state.value.copy(
            selectedMainLocation = location,
        )
    }

    fun selectDate(date: String) {
        _state.value = _state.value.copy(selectedDate = date)
        viewModelScope.launch {
            getFindSuhyeonAllPost(
                _state.value.selectedSubLocation.orEmpty(),
                date
            )
        }
    }
}