package com.sopt.withsuhyeon.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun BasicShortTextField(
    value: String,
    hint: String,
    isValid: Boolean,
    enabled: Boolean,
    onFocusChange: (Boolean) -> Unit,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }
    val borderColor =
        if(isFocused)
            colors.Purple300
        else if (value.isNotEmpty() && enabled && !isValid)
            colors.Red01
        else colors.Grey100
    val textColor = if (enabled) colors.Grey900 else colors.Grey300
    val backgroundColor = if (enabled) colors.White else colors.Grey100
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        value = value,
        enabled = enabled,
        onValueChange = onValueChange,
        textStyle = typography.body03_R.merge(color = textColor),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = hint,
                        style = typography.body03_R.merge(color = colors.Grey300)
                    )
                }
                innerTextField()

            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                keyboardController?.hide()
            }
        ),
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(7.dp),
            )
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .height(52.dp)
            .onFocusChanged {
                isFocused = it.isFocused
                onFocusChange(isFocused)
            }
    )
}

@Composable
@Preview
fun  PreviewShortTextField() {
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
            if(it == "텍스트")
                isValid = true
            if(it == "불가")
                enabled = false
        }
    )
}