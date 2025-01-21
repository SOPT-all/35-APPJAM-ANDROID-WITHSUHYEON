package com.sopt.withsuhyeon.feature.home.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun HomeCardItem(
    title: String,
    @DrawableRes imageResource: Int,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(
                RoundedCornerShape(20.dp)
            )
            .width(202.dp)
            .height(173.dp)
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = null,
            modifier = Modifier.width(202.dp)
                .height(173.dp)
                .clip(
                    RoundedCornerShape(20.dp)
                )
        )
        Text(
            text = title,
            style = typography.body02_B.merge(color = textColor),
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.TopStart)
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                )
        )
    }
}
@Preview
@Composable
fun PreviewHomeCardItem() {
    HomeCardItem(
        "쉿! 여행 중엔\n" +
                "방해 금지 모드",
        imageResource = R.drawable.img_do_not_disturb,
        textColor = colors.White
    )
}