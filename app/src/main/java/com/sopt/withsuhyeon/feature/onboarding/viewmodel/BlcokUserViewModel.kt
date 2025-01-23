package com.sopt.withsuhyeon.feature.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.data.dto.request.RequestBlockUserDto
import com.sopt.withsuhyeon.domain.repository.BlockUserRepository
import com.sopt.withsuhyeon.feature.onboarding.state.BlockUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlcokUserViewModel @Inject constructor(
    private val blockUserRepository: BlockUserRepository
) : ViewModel() {
    private val _blockUserState = MutableStateFlow(BlockUserState())
    val blockUserState: StateFlow<BlockUserState> = _blockUserState


    fun selectBlockUserNumber(phoneNumber: String) {
        _blockUserState.update {
            it.copy(
                blockNumber = phoneNumber
            )
        }
    }

    fun getBlockUser() {
        viewModelScope.launch {
            blockUserRepository.getBlockUser().onSuccess { response ->
                _blockUserState.update { current ->
                    current.copy(
                        blockNumbers = response.phoneNumbers
                    )
                }
            }
        }
    }

    fun postBlockUser(blockNumber: String) {
        viewModelScope.launch {
            blockUserRepository.postBlockUser(blockNumber).onSuccess {
                getBlockUser()
            }
        }
    }

    fun deleteBlockUser(blockNumber: String) {
        viewModelScope.launch {
            blockUserRepository.deleteBlockUser(number = blockNumber)
            getBlockUser()
        }
    }
}
