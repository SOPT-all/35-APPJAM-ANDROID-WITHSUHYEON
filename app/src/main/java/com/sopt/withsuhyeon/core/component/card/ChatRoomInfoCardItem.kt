package com.sopt.withsuhyeon.core.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.core.component.button.NavigateToPostButton
import com.sopt.withsuhyeon.core.util.price.toDecimalFormat
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun ChatRoomInfoCardItem(
    region: String,
    postTitle: String,
    price: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = region,
                    style = typography.caption01_B,
                    color = colors.Grey400
                )
                Text(
                    text = postTitle,
                    style = typography.body03_B,
                    color = colors.Grey950
                )
                Text(
                    text = "${price.toDecimalFormat()}원",
                    style = typography.body03_B,
                    color = colors.Grey950
                )
            }
            NavigateToPostButton {}
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = colors.Grey100
        )
    }
}

@Preview
@Composable
private fun ChatRoomInfoCardItemPreview() {
    WithSuhyeonTheme {
        ChatRoomInfoCardItem("강남/역삼/삼성", "강남역에서 수현이 되어주실 분!", 20000)
    }
}