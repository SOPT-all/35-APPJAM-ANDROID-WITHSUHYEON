package com.sopt.withsuhyeon.core.component.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.KeyStorage.LONG_TEXTFIELD_MAX_LENGTH
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun LongTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    maxLength: Int = LONG_TEXTFIELD_MAX_LENGTH
) {
    var isFocused by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    val borderColor = when {
        isError -> colors.Red01
        isFocused -> colors.Purple400
        else -> colors.Grey100
    }

    Column(
        modifier = modifier
    ) {
        TextField(
            value = value,
            onValueChange = { input ->
                if (input.length <= maxLength) {
                    isError = false
                    onValueChange(input)
                } else {
                    isError = true
                    onValueChange(input.substring(0, maxLength))
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 188.dp)
                .border(1.dp, borderColor, RoundedCornerShape(12.dp))
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                    if (!isError && focusState.isFocused) {
                        isFocused = true
                    }
                },
            maxLines = Int.MAX_VALUE,
            textStyle = typography.body03_SB,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Transparent,
                unfocusedContainerColor = Transparent,
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isError) stringResource(R.string.long_text_field_max_length_error_message) else stringResource(R.string.long_text_field_max_length_no_message),
                color = if (isError) colors.Red01 else Transparent,
                style = typography.body03_R
            )

            Text(
                text = "${value.length}/200",
                color = if (isError) colors.Red01 else colors.Grey400,
                style = typography.body03_R,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}