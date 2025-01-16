package com.sopt.withsuhyeon.core.component.listitem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun BlockPhoneNumberItem(
    phoneNumber: String,
    backgroundColor: Color = colors.Grey25,
    phoneNumberColor: Color = colors.Grey900,
    font: TextStyle = typography.body03_SB,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(start = 16.dp, end = 4.dp, top = 15.dp, bottom = 15.dp),

        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = phoneNumber,
            color = phoneNumberColor,
            style = font,
        )
        Box(
            modifier = Modifier.padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_xclose),
                contentDescription = stringResource(R.string.x_close_description),
                tint = colors.Grey400
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBlockPhoneNumberList(
    modifier: Modifier = Modifier
) {
    WithSuhyeonTheme {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            BlockPhoneNumberItem(
                phoneNumber = "01000000000"
            )
            BlockPhoneNumberItem(
                phoneNumber = "01000000000"
            )
            BlockPhoneNumberItem(
                phoneNumber = "01000000000"
            )
        }
    }
}