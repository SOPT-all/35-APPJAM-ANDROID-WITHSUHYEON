package com.sopt.withsuhyeon.core.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun GalleryMainCardItem(
    text: String,
    image: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .noRippleClickable { onClick() },
        horizontalAlignment = Alignment.Start
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(180.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(colors.Grey200)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = text,
            style = typography.caption01_SB,
            color = colors.Black
        )
    }
}

@Preview (showBackground = true)
@Composable
private fun GalleryMainCardItemPreview() {
    WithSuhyeonTheme {
        GalleryMainCardItem("술자리 사진 모음집", "https://via.placeholder.com/150", {})
    }
}