package com.sopt.withsuhyeon.core.component.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun PostProfileInfoRow(
    profileImage: String,
    userName: String,
    date: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 11.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        val profileImageResId = when (profileImage) {
            stringResource(R.string.suma_img_purple) -> R.drawable.img_purple_suma
            stringResource(R.string.suma_img_red) -> R.drawable.img_red_suma
            stringResource(R.string.suma_img_green) -> R.drawable.img_green_suma
            stringResource(R.string.suma_img_blue) -> R.drawable.img_blue_suma
            else -> ""
        }

        AsyncImage(
            model = profileImageResId,
            contentDescription = stringResource(R.string.post_basic_profile_image_description),
            modifier = Modifier
                .size(38.dp)
                .clip(RoundedCornerShape(200.dp))
                .background(colors.Grey100)
        )

        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = userName,
                style = typography.body03_SB
            )
            Spacer(modifier.height(4.dp))
            Text(
                text = date,
                style = typography.caption01_R,
                color = colors.Grey500
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    PostProfileInfoRow(
        "https://via.placeholder.com/150",
        "작심이",
        "1월 12일"
    )
}