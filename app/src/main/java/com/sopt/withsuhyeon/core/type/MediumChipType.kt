package com.sopt.withsuhyeon.core.type

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.ui.theme.defaultWithSuhyeonColors
import com.sopt.withsuhyeon.ui.theme.defaultWithSuhyeonTypography

enum class MediumChipType(
    @StringRes val titleResId: Int,
    val backgroundColor: Color,
    val textColor: Color,
    val horizontalPadding: Dp = 8.dp,
    val verticalPadding: Dp = 4.dp,
    val cornerRadius: Dp = 8.dp,
    val typography: TextStyle = defaultWithSuhyeonTypography.caption01_SB,
    val requiresDynamicString: Boolean = false
) {
    CATEGORY(
        titleResId = R.string.type_category,
        backgroundColor = defaultWithSuhyeonColors.Purple50,
        textColor = defaultWithSuhyeonColors.Purple400,
        requiresDynamicString = true
    ),
    CATEGORY_PHOTO(
        titleResId = R.string.type_category_photo,
        backgroundColor = defaultWithSuhyeonColors.Purple50,
        textColor = defaultWithSuhyeonColors.Purple400,
        requiresDynamicString = false
    ),
    CATEGORY_VIDEO_CALL(
        titleResId = R.string.type_category_video_call,
        backgroundColor = defaultWithSuhyeonColors.Purple50,
        textColor = defaultWithSuhyeonColors.Purple400,
        requiresDynamicString = true
    ),
    CATEGORY_PHONE_CALL(
        titleResId = R.string.type_category_phone_call,
        backgroundColor = defaultWithSuhyeonColors.Purple50,
        textColor = defaultWithSuhyeonColors.Purple400,
        requiresDynamicString = true
    ),
    MATCH(
        titleResId = R.string.type_match,
        backgroundColor = defaultWithSuhyeonColors.Grey100,
        textColor = defaultWithSuhyeonColors.Grey500,
        requiresDynamicString = false
    ),
    FINISHED(
        titleResId = R.string.type_finished,
        backgroundColor = defaultWithSuhyeonColors.Grey400,
        textColor = defaultWithSuhyeonColors.White,
        requiresDynamicString = false
    ),
    CHAT(
        titleResId = R.string.type_chat,
        backgroundColor = defaultWithSuhyeonColors.Purple50,
        textColor = defaultWithSuhyeonColors.Purple900,
        cornerRadius = 16.dp,
        requiresDynamicString = true
    ),
}