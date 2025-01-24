package com.sopt.withsuhyeon.core.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun BasicLongTextField(
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
    textFieldBorderColor: Color? = null,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingContent: @Composable (BoxScope.() -> Unit) = {},
    onFocusChange: (Boolean) -> Unit = {},
) {
    var isFocused by remember { mutableStateOf(false) }
    val borderColor =
        textFieldBorderColor
            ?: when {
                value.isNotEmpty() && !isValid -> colors.Red01
                isFocused -> colors.Purple300
                !isValid -> colors.Red01
                else -> colors.Grey100
            }
    val textColor = if (enabled) colors.Grey900 else colors.Grey300
    val backgroundColor = if (enabled) colors.White else colors.Grey100

    Column(
        modifier = modifier.fillMaxWidth()
            .wrapContentHeight(),
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
                onValueChange(input)
            },
            textStyle = typography.body03_R.merge(color = textColor),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.Top
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
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 188.dp)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(7.dp),
                )
                .border(1.dp, borderColor, RoundedCornerShape(12.dp))
                .onFocusChanged {
                    isFocused = it.isFocused
                    onFocusChange(isFocused)
                }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = errorMessage,
                style = typography.body03_R.merge(color = colors.Red01),
                modifier = Modifier.weight(1f)
            )
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