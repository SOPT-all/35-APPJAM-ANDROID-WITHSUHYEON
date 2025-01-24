package com.sopt.withsuhyeon.feature.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.domain.entity.MyPageInfo
import com.sopt.withsuhyeon.domain.repository.MyPageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val myPageRepository: MyPageRepository
) : ViewModel() {
    private val _myPageInfo = MutableStateFlow(MyPageInfo(nickname = "", profileImage = ""))
    val myPageInfo: StateFlow<MyPageInfo> = _myPageInfo.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun getMyPageInfo() {
        viewModelScope.launch {
            myPageRepository.getMyPageInfo()
                .onSuccess {
                    _myPageInfo.value = MyPageInfo(
                        nickname = it.nickname,
                        profileImage = it.profileImage
                    )
                }
                .onFailure { error ->
                    _errorMessage.value = error.message ?: "Unknown error"
                }
        }
    }
}