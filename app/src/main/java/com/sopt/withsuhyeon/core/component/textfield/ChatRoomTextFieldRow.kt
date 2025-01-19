package com.sopt.withsuhyeon.core.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun ChatRoomTextFieldRow(
    text: String,
    onTextChange: (String) -> Unit,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isMessageTextFieldNotEmpty = text.isNotBlank()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.White)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = text,
            onValueChange = onTextChange,
            placeholder = {
                Text(
                    text = stringResource(R.string.chatroom_textfield_hint),
                    style = typography.body03_R,
                    color = colors.Grey400
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = colors.Grey900,
                unfocusedTextColor = colors.Grey900
            ),
            modifier = Modifier
                .weight(1f)
                .heightIn(min = 40.dp)
                .background(
                    color = colors.Grey50,
                    shape = RoundedCornerShape(24.dp)
                ),
            maxLines = 5,
            textStyle = typography.body03_SB
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = if (isMessageTextFieldNotEmpty) colors.Purple50 else colors.Grey50,
                    shape = RoundedCornerShape(20.dp)
                )
                .noRippleClickable(onClick = onSendClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_send),
                contentDescription = stringResource(R.string.message_textfield_send_button_description),
                tint = if (isMessageTextFieldNotEmpty) colors.Purple500 else colors.Grey300
            )
        }
    }
}