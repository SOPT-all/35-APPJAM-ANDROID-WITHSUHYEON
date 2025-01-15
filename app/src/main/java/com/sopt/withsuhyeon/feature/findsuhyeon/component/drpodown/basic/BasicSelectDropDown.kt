package com.sopt.withsuhyeon.feature.findsuhyeon.component.drpodown.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.feature.findsuhyeon.component.drpodown.text.TextDropDown
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun BasicSelectDropDown(
    isError: Boolean,
    modifier: Modifier = Modifier,
    errorMessage: String = "",
    onClick: () -> Unit,
    mainContent: @Composable (RowScope.() -> Unit) = {}
) {
    val borderAndErrorTextColor = if(isError)
        colors.Red01
    else
        colors.Grey100

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colors.White,
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 1.dp,
                    color = borderAndErrorTextColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 12.dp, vertical = 14.dp)
                .noRippleClickable(onClick)
        ) {
            mainContent()
        }
        Text(
            text = if(isError) errorMessage else stringResource(R.string.find_suhyeon_no_message),
            style = typography.body03_R.merge(color = borderAndErrorTextColor)
        )
    }
}

@Preview
@Composable
fun PreviewBasicSelectDropDown() {
    val value by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    TextDropDown(
        value = value,
        hint = "눌러서 나이 선택하기",
        isError = isError,
        errorMessage = errorMessage,
        onClick = {
            isError = !isError
            if(isError)
                errorMessage = "필수로 입력해줘"
        }
    )
}