package com.sopt.withsuhyeon.core.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.BasicButtonForTextField
import com.sopt.withsuhyeon.core.util.KeyStorage.SHORT_TEXTFIELD_MAX_LENGTH
import com.sopt.withsuhyeon.core.util.modifier.addFocusCleaner
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun BasicShortTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    title: String = "",
    errorMessage: String = "",
    isValid: Boolean = true,
    enabled: Boolean = true,
    visibleLength: Boolean = false,
    maxLength: Int = -1,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingContent: @Composable (BoxScope.() -> Unit) = {},
    onFocusChange: (Boolean) -> Unit = {},
) {
    var isFocused by remember { mutableStateOf(false) }
    var isMaxLengthExceeded by remember { mutableStateOf(false) }
    val borderColor =
        when {
            value.isNotEmpty() && enabled && !isValid -> colors.Red01
            isMaxLengthExceeded -> colors.Red01
            isFocused -> colors.Purple300
            else -> colors.Grey100
        }
    val textColor = if (enabled) colors.Grey900 else colors.Grey300
    val backgroundColor = if (enabled) colors.White else colors.Grey100

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        if (title.isNotEmpty()) {
            Text(
                text = title,
                style = typography.body03_SB.merge(color = colors.Grey600)
            )
        }
        BasicTextField(
            value = value,
            enabled = enabled,
            onValueChange = { input ->
                if (maxLength > 0 && input.length > maxLength) {
                    isMaxLengthExceeded = true
                    onValueChange(input.take(maxLength))
                } else {
                    isMaxLengthExceeded = false
                    onValueChange(input)
                }
            },
            textStyle = typography.body03_R.merge(color = textColor),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = hint,
                                style = typography.body03_R.merge(color = colors.Grey300)
                            )
                        }
                        innerTextField()
                    }
                    Box(
                        modifier = Modifier
                            .wrapContentWidth(align = Alignment.End)
                            .padding(start = 10.dp)
                    ) {
                        trailingContent()
                    }
                }
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isMaxLengthExceeded) {
                Text(
                    text = stringResource(
                        R.string.short_text_field_text_max_length_message,
                        maxLength
                    ),
                    style = typography.body03_R.merge(color = colors.Red01),
                    modifier = Modifier.weight(1f)
                )
            } else if (value.isNotEmpty()
                && !isValid
                && enabled
                && errorMessage.isNotEmpty()
            ) {
                Text(
                    text = errorMessage,
                    style = typography.body03_R.merge(color = colors.Red01),
                    modifier = Modifier.weight(1f)
                )
            } else {
                Text(
                    text = "",
                    style = typography.body03_R.merge(color = colors.Red01),
                    modifier = Modifier.weight(1f)
                )
            }
            if (visibleLength && maxLength != -1) {
                Text(
                    text = stringResource(
                        R.string.short_text_field_character_length,
                        value.length,
                        maxLength
                    ),
                    style = typography.caption01_R.merge(color = colors.Grey400),
                    modifier = Modifier.align(Alignment.Bottom)
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewBasicShortTextField() {
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

@Composable
@Preview
fun PreviewFullOptionBasicShortTextField() {
    var value by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isValid by remember { mutableStateOf(false) }
    var enabled by remember { mutableStateOf(true) }
    var isFocused by remember { mutableStateOf(false) }

    //Button
    var buttonText by remember { mutableStateOf("인증 요청") }
    var buttonEnabled by remember { mutableStateOf(true) }

    val maxLength = SHORT_TEXTFIELD_MAX_LENGTH
    Column(
        modifier = Modifier
            .fillMaxSize()
            .addFocusCleaner(LocalFocusManager.current)
    ) {
        BasicShortTextField(
            value = value,
            hint = "텍스트를 입력해주세요",
            title = "제목",
            isValid = isValid,
            enabled = enabled,
            onFocusChange = {
                isFocused = it
            },
            onValueChange = { input ->
                isValid = input == "텍스트"
                errorMessage = if (!isValid) "\"텍스트\"라고 입력해주세요." else ""
                value = input
                enabled = value != "불가"

            },
            errorMessage = errorMessage,
            visibleLength = true,
            maxLength = maxLength,
            trailingContent = {
                BasicButtonForTextField(
                    text = buttonText,
                    enabled = buttonEnabled,
                    onClick = {
                        buttonEnabled = !buttonEnabled
                        buttonText = if (buttonEnabled) "인증 요청" else "전송 완료"
                    }
                )
            }
        )
    }
}