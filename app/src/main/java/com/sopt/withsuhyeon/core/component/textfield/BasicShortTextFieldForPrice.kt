package com.sopt.withsuhyeon.core.component.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.price.thousandSeparatorTransformation
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun BasicShortTextFieldForPrice(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String = "",
    hint: String = "",
    isValid: Boolean = true,
    enabled: Boolean = true,
    textFieldBorderColor: Color? = null,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onFocusChange: (Boolean) -> Unit = {},
    onButtonClick: (String) -> Unit = {}
) {
    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        BasicShortTextField(
            value = value,
            onValueChange = { input ->
                onValueChange(input)
            },
            hint = hint,
            isValid = value.isNotEmpty(),
            enabled = enabled,
            onFocusChange = {
                isFocused = it
                onFocusChange(it)
            },
            modifier = modifier,
            textFieldBorderColor = textFieldBorderColor,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            keyboardActions = keyboardActions,
            visualTransformation = thousandSeparatorTransformation(),
            trailingContent = {
                Text(
                    text = stringResource(R.string.short_text_field_text_won),
                    color = colors.Grey400,
                    style = typography.body02_B,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            },
            errorMessage = errorMessage
        )
    }
}


@Composable
@Preview
fun PreviewBasicShortTextFieldForPrice() {
    var value by remember { mutableStateOf("") }
    var isValid by remember { mutableStateOf(false) }
    var enabled by remember { mutableStateOf(true) }
    var isFocused by remember { mutableStateOf(false) }
    BasicShortTextFieldForPrice(
        value = value,
        hint = "텍스트를 입력해주세요",
        isValid = isValid,
        enabled = enabled,
        onFocusChange = {
            isFocused = it
        },
        onValueChange = {
            value = it
            if (it == "1234")
                isValid = true
            if (it == "12345")
                enabled = false
        }
    )
}