package com.sopt.withsuhyeon.core.type

import androidx.annotation.StringRes
import com.sopt.withsuhyeon.R

enum class ProfileType(
    @StringRes val titleResId: Int,
) {
    BLUE_SUMA(titleResId = R.drawable.img_blue_suma),
    RED_SUMA(titleResId = R.drawable.img_red_suma),
    GREEN_SUMA(titleResId = R.drawable.img_green_suma),
    PURPLE_SUMA(titleResId = R.drawable.img_purple_suma)
}