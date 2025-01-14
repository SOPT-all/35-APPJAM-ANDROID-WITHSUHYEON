package com.sopt.withsuhyeon.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun CheckBox(
    placeholder: String,
    type: String = "primary",
    state: String = "default",
    modifier: Modifier = Modifier,
    font: TextStyle = typography.body03_SB,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val backgroundColor: Color
        val checkColor: Color
        val textColor: Color
        when (state) {
            "checked" -> {
                when (type) {
                    "secondary" -> {
                        backgroundColor = colors.Purple50
                        checkColor = colors.Purple500
                    }

                    else -> {
                        backgroundColor = colors.Purple500
                        checkColor = colors.White
                    }
                }
                textColor = colors.Black
            }

            "disabled" -> {
                backgroundColor = colors.Grey100
                checkColor = colors.Grey300
                textColor = colors.Grey400
            }

            else -> {
                when (type) {
                    "secondary" -> {
                        backgroundColor = colors.Grey50
                        checkColor = colors.Grey200
                    }

                    else -> {
                        backgroundColor = colors.Grey200
                        checkColor = colors.White
                    }
                }
                textColor = colors.Black
            }
        }
        Box(
            modifier = modifier
                .width(20.dp)
                .height(20.dp)
                .padding(horizontal = 4.dp, vertical = 4.dp)
                .background(backgroundColor, shape = RoundedCornerShape(size = 6.dp))
                .noRippleClickable(onClick)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check_16),
                contentDescription = stringResource(R.string.checkbox_image_name),
                tint = checkColor
            )
        }
        Text(
            text = placeholder,
            color = textColor,
            style = font
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CheckBoxPreview(
    modifier: Modifier = Modifier
) {
    WithSuhyeonTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(colors.White)
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            CheckBox(
                placeholder = "플레이스 홀더",
                onClick = {}
            )
            CheckBox(
                placeholder = "플레이스 홀더",
                state = "checked",
                onClick = {}
            )
            CheckBox(
                placeholder = "플레이스 홀더",
                state = "disabled",
                onClick = {}
            )
            CheckBox(
                placeholder = "플레이스 홀더",
                type = "secondary",
                onClick = {}
            )
            CheckBox(
                placeholder = "플레이스 홀더",
                type = "secondary",
                state = "checked",
                onClick = {}
            )
            CheckBox(
                placeholder = "플레이스 홀더",
                type = "secondary",
                state = "disabled",
                onClick = {}
            )
        }
    }
}
