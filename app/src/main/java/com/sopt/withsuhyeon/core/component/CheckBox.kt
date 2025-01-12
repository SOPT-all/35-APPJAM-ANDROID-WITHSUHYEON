package com.sopt.withsuhyeon.core.component

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.Black
import com.sopt.withsuhyeon.ui.theme.Grey100
import com.sopt.withsuhyeon.ui.theme.Grey200
import com.sopt.withsuhyeon.ui.theme.Grey300
import com.sopt.withsuhyeon.ui.theme.Grey400
import com.sopt.withsuhyeon.ui.theme.Grey50
import com.sopt.withsuhyeon.ui.theme.Purple50
import com.sopt.withsuhyeon.ui.theme.Purple500
import com.sopt.withsuhyeon.ui.theme.White
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.defaultWithSuhyeonTypography

@Composable
fun CheckBox(
    placeholder: String,
    type: String = "primary",
    state: String = "default",
    modifier: Modifier = Modifier,
    font: TextStyle = defaultWithSuhyeonTypography.body03_SB,
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
                        backgroundColor = Purple50
                        checkColor = Purple500
                    }
                    else -> {
                        backgroundColor = Purple500
                        checkColor = White
                    }
                }
                textColor = Black
            }

            "disabled" -> {
                backgroundColor = Grey100
                checkColor = Grey300
                textColor = Grey400
            }

            else -> {
                when (type) {
                    "secondary" -> {
                        backgroundColor = Grey50
                        checkColor = Grey200
                    }
                    else -> {
                        backgroundColor = Grey200
                        checkColor = White
                    }
                }
                textColor = Black
            }
        }
        Box(
            modifier = modifier
                .width(20.dp)
                .height(20.dp)
                .padding(horizontal = 2.dp, vertical = 2.dp)
                .background(backgroundColor, shape = RoundedCornerShape(size = 6.dp))
                .noRippleClickable(onClick)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_check_16),
                contentDescription = "check",
                colorFilter = ColorFilter.tint(checkColor)
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
                .background(White)
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
