package com.sopt.withsuhyeon.core.component.listitem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.chip.SmallChip
import com.sopt.withsuhyeon.core.type.SmallChipType
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun ChatRoomListItem(
    profileImage: String,
    partnerName: String,
    recentChat: String,
    recentChatDateTime: String,
    unreadChatCount: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = profileImage,
                contentDescription = stringResource(R.string.post_basic_profile_image_description),
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(200.dp))
                    .background(colors.Grey100)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = partnerName,
                    style = typography.body02_B,
                    color = colors.Grey900,
                    maxLines = 1
                )
                Text(
                    text = recentChat,
                    style = typography.body03_SB,
                    color = colors.Grey600,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    softWrap = false
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = recentChatDateTime,
                    style = typography.caption01_SB,
                    color = colors.Grey400
                )
                Spacer(modifier = Modifier.height(8.dp))
                SmallChip(
                    smallChipType = SmallChipType.CHAT_COUNT,
                    dynamicString = unreadChatCount
                )
            }
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = colors.Grey100
        )
    }
}

@Preview (showBackground = true)
@Composable
private fun ChatRoomListItemPreview() {
    WithSuhyeonTheme {
        ChatRoomListItem("https://via.placeholder.com/150", "작심이", "나는야 작심이 작심이 작심이 작심이 작심이 작심이", "1월 1일", "+99")
    }
}