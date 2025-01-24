package com.sopt.withsuhyeon.core.type

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.ui.theme.defaultWithSuhyeonColors

enum class ProfileType(
    @StringRes val titleResId: Int,
    val borderColor: Color,
    val title: String,
) {
    PURPLE_SUMA(
        titleResId = R.drawable.img_purple_suma,
        borderColor = defaultWithSuhyeonColors.Purple100,
        title = "img_purple_suma"
    ),
    RED_SUMA(
        titleResId = R.drawable.img_red_suma,
        borderColor = defaultWithSuhyeonColors.Red02,
        title = "img_red_suma"
    ),
    GREEN_SUMA(
        titleResId = R.drawable.img_green_suma,
        borderColor = Color(0xFFA9EC66),
        title = "img_green_suma"
    ),
    BLUE_SUMA(
        titleResId = R.drawable.img_blue_suma,
        borderColor = Color(0xFF94C4FF),
        title = "img_blue_suma"
    )
}