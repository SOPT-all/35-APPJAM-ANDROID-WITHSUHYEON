package com.sopt.withsuhyeon.feature.onboarding.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING
import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.domain.repository.BlockUserRepository
import com.sopt.withsuhyeon.feature.onboarding.state.BlockUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json.Default.decodeFromString
import javax.inject.Inject

@HiltViewModel
class BlockUserViewModel @Inject constructor(
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
                        blockNumbers = response.phoneNumbers,
                        nickname = response.nickname
                    )
                }
            }
        }
    }

    fun postBlockUser(
        blockNumber: String,
        onError: (String) -> Unit
        ) {
        viewModelScope.launch {
            blockUserRepository.postBlockUser(blockNumber).onSuccess {
                getBlockUser()
                Log.d("onSuccess", "")
            }
                .onFailure { error ->
                    val errorMessage = when (error) {
                        is retrofit2.HttpException -> {
                            val errorBody = error.response()?.errorBody()?.string()
                            try {
                                val baseResponse =
                                    decodeFromString<BaseResponse<Unit>>(errorBody ?: "")
                                baseResponse.message ?: "알 수 없는 에러가 발생했습니다."
                            } catch (e: Exception) {
                                "에러 응답 파싱 실패: ${e.message}"
                            }
                        }

                        else -> {
                            "알 수 없는 에러가 발생했습니다: ${error.message}"
                        }
                    }
                    onError(errorMessage)
                    _blockUserState.update {
                        it.copy(
                            errorMessage = errorMessage
                        )
                    }
                }
        }
    }

    fun refreshErrorMessage() {
        _blockUserState.update {
            it.copy(
                errorMessage = EMPTY_STRING
            )
        }
    }

    fun deleteBlockUser(blockNumber: String) {
        viewModelScope.launch {
            blockUserRepository.deleteBlockUser(number = blockNumber)
                .onSuccess {
                    Log.d("onSuccess", "")
                }
                .onFailure { error ->
                    Log.d("error", error.message.toString())

                }
            getBlockUser()
        }
    }
}
