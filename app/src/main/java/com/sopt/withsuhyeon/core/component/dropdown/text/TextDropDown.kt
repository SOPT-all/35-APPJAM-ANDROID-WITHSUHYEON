package com.sopt.withsuhyeon.core.component.dropdown.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.withsuhyeon.core.component.dropdown.basic.BasicSelectDropDown

@Composable
fun TextDropDown(
    hint: String,
    isError: Boolean,
    modifier: Modifier = Modifier,
    value: String?,
    errorMessage: String = "",
    onClick: (String?) -> Unit = {},
    startContent: @Composable () -> Unit = {},
) {
    BasicSelectDropDown(
        isError = isError,
        errorMessage = errorMessage,
        modifier = modifier,
        onClick = { onClick(value) },
        mainContent = {
            TextDropDownItem(
                modifier = Modifier.weight(1f),
                value = value,
                hint = hint,
                startContent = startContent
            )
        }
    )
}

@Preview
@Composable
fun PreviewTextDropDown() {
    val value by remember { mutableStateOf<String?>(null) }
    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    TextDropDown(
        value = value,
        hint = "눌러서 나이 선택하기",
        isError = isError,
        errorMessage = errorMessage,
        onClick = {
            isError = !isError
            if (isError)
                errorMessage = "필수로 입력해줘"
        }
    )
}