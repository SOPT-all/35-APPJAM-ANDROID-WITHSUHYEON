package com.sopt.withsuhyeon.feature.findsuhyeon.component.drpodown.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun TextDropDownItem(
    hint: String,
    modifier: Modifier = Modifier,
    value: String? = "",
    startContent: @Composable () -> Unit = {},
) {
    val textTypography = if(value.isNullOrEmpty())
        typography.body03_R
    else
        typography.body03_SB

    val textColor = if(value.isNullOrEmpty())
        colors.Grey400
    else
        colors.Grey950
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        startContent()
        Text(
            text = if(value.isNullOrEmpty()) hint else value,
            style = textTypography.merge(color = textColor),
            modifier = Modifier.weight(1f)
        )
    }
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_down),
        contentDescription = stringResource(R.string.find_suhyeon_detail_meeting_information),
        tint = colors.Grey400,
        modifier = Modifier
            .size(24.dp)
    )
}

@Preview
@Composable
fun PreviewTextDropDownItem() {
    val value by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextDropDownItem(
            modifier = Modifier.weight(1f),
            value = value,
            hint = "눌러서 나이 선택하기",
        )
    }
}