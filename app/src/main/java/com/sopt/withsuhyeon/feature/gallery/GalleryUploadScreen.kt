package com.sopt.withsuhyeon.feature.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.dropdown.text.TextDropDown
import com.sopt.withsuhyeon.core.component.textfield.BasicShortTextField
import com.sopt.withsuhyeon.core.component.textfield.LongTextField
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun GalleryUploadRoute(
    padding: PaddingValues,
    viewModel: GalleryViewModel = hiltViewModel()
) {
    GalleryUploadScreen(
        modifier = Modifier.fillMaxSize()
            .padding(padding)
    )
}

@Composable
private fun GalleryUploadScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        val value by remember { mutableStateOf("") }
        var isError by remember { mutableStateOf(false) }
        var errorMessage by remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    GalleryUploadWithPicker({})
                }

                item {
                    TextDropDown(
                        value = value,
                        hint = "눌러서 카테고리 선택하기",
                        isError = isError,
                        errorMessage = errorMessage,
                        onClick = {
                            isError = !isError
                            if(isError)
                                errorMessage = "필수로 입력해줘"
                        }
                    )
                }

                item {
                    var value by remember { mutableStateOf("") }
                    var isValid by remember { mutableStateOf(false) }
                    var enabled by remember { mutableStateOf(true) }
                    var isFocused by remember { mutableStateOf(false) }
                    BasicShortTextField(
                        value = value,
                        hint = "텍스트를 입력해주세요",
                        isValid = isValid,
                        enabled = enabled,
                        onFocusChange = {
                            isFocused = it
                        },
                        onValueChange = {
                            value = it
                            if (it == "텍스트")
                                isValid = true
                            if (it == "불가")
                                enabled = false
                        }
                    )
                }

                item {
                    LongTextField(
                        value = "",
                        onValueChange = {}
                    )
                }

                item {
                    LongTextField(
                        value = "",
                        onValueChange = {}
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.White)
                .padding(16.dp)
        ) {
            LargeButton("완료") { }
        }
    }
}

@Preview (showBackground = true)
@Composable
private fun GalleryUploadScreenPreview() {
    WithSuhyeonTheme {
        GalleryUploadScreen()
    }
}