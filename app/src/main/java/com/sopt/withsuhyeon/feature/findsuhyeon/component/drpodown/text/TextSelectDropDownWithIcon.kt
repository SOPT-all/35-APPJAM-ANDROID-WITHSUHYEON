package com.sopt.withsuhyeon.feature.findsuhyeon.component.drpodown.text

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun TextSelectDropDownWithIcon(
    hint: String,
    isError: Boolean,
    modifier: Modifier = Modifier,
    value: String = "",
    errorMessage: String = "",
    onClick: () -> Unit,
) {
    TextDropDown(
        value = value,
        hint = hint,
        isError = isError,
        errorMessage = errorMessage,
        modifier = modifier,
        onClick = onClick,
        startContent = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_location),
                contentDescription = stringResource(R.string.find_suhyeon_location),
                tint = colors.Grey500,
                modifier = Modifier
                    .size(24.dp)
            )
        }
    )
}
@Preview
@Composable
fun PreviewSTextSelectDropDownWithIcon() {
    var value by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    TextSelectDropDownWithIcon(
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