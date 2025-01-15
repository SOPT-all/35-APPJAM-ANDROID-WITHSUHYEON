package com.sopt.withsuhyeon.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sopt.withsuhyeon.R

val withSuhyeonFontBold = FontFamily(Font(R.font.suit_bold))
val withSuhyeonFontSemiBold = FontFamily(Font(R.font.suit_semibold))
val withSuhyeonFontRegular = FontFamily(Font(R.font.suit_regular))

@Immutable
data class WithSuhyeonTypography(
    val heading01_B: TextStyle,
    val heading01_SB: TextStyle,
    val heading01_R: TextStyle,
    val heading02_B: TextStyle,
    val heading02_SB: TextStyle,
    val heading02_R: TextStyle,
    val title01_B: TextStyle,
    val title01_SB: TextStyle,
    val title01_R: TextStyle,
    val title02_B: TextStyle,
    val title02_SB: TextStyle,
    val title02_R: TextStyle,
    val title03_B: TextStyle,
    val title03_SB: TextStyle,
    val title03_R: TextStyle,
    val body01_B: TextStyle,
    val body01_SB: TextStyle,
    val body01_R: TextStyle,
    val body02_B: TextStyle,
    val body02_SB: TextStyle,
    val body02_R: TextStyle,
    val body03_B: TextStyle,
    val body03_SB: TextStyle,
    val body03_R: TextStyle,
    val caption01_B: TextStyle,
    val caption01_SB: TextStyle,
    val caption01_R: TextStyle,
    val caption02_R: TextStyle,
    val caption02_SB: TextStyle,
    val caption02_B: TextStyle
)

val defaultWithSuhyeonTypography = WithSuhyeonTypography(
    heading01_B = TextStyle(
        fontFamily = withSuhyeonFontBold,
        fontSize = 32.sp,
        fontWeight = FontWeight(700),
        lineHeight = 44.sp
    ),
    heading01_SB = TextStyle(
        fontFamily = withSuhyeonFontSemiBold,
        fontSize = 32.sp,
        fontWeight = FontWeight(600),
        lineHeight = 44.sp
    ),
    heading01_R = TextStyle(
        fontFamily = withSuhyeonFontRegular,
        fontSize = 32.sp,
        fontWeight = FontWeight(400),
        lineHeight = 44.sp
    ),
    heading02_B = TextStyle(
        fontFamily = withSuhyeonFontBold,
        fontSize = 28.sp,
        fontWeight = FontWeight(700),
        lineHeight = 40.sp
    ),
    heading02_SB = TextStyle(
        fontFamily = withSuhyeonFontSemiBold,
        fontSize = 28.sp,
        fontWeight = FontWeight(600),
        lineHeight = 40.sp
    ),
    heading02_R = TextStyle(
        fontFamily = withSuhyeonFontRegular,
        fontSize = 28.sp,
        fontWeight = FontWeight(400),
        lineHeight = 40.sp
    ),
    title01_B = TextStyle(
        fontFamily = withSuhyeonFontBold,
        fontSize = 24.sp,
        fontWeight = FontWeight(700),
        lineHeight = 36.sp
    ),
    title01_SB = TextStyle(
        fontFamily = withSuhyeonFontSemiBold,
        fontSize = 24.sp,
        fontWeight = FontWeight(600),
        lineHeight = 36.sp
    ),
    title01_R = TextStyle(
        fontFamily = withSuhyeonFontRegular,
        fontSize = 24.sp,
        fontWeight = FontWeight(400),
        lineHeight = 36.sp
    ),
    title02_B = TextStyle(
        fontFamily = withSuhyeonFontBold,
        fontSize = 22.sp,
        fontWeight = FontWeight(700),
        lineHeight = 34.sp
    ),
    title02_SB = TextStyle(
        fontFamily = withSuhyeonFontSemiBold,
        fontSize = 22.sp,
        fontWeight = FontWeight(600),
        lineHeight = 34.sp
    ),
    title02_R = TextStyle(
        fontFamily = withSuhyeonFontRegular,
        fontSize = 22.sp,
        fontWeight = FontWeight(400),
        lineHeight = 34.sp
    ),
    title03_B = TextStyle(
        fontFamily = withSuhyeonFontBold,
        fontSize = 20.sp,
        fontWeight = FontWeight(700),
        lineHeight = 30.sp
    ),
    title03_SB = TextStyle(
        fontFamily = withSuhyeonFontSemiBold,
        fontSize = 20.sp,
        fontWeight = FontWeight(600),
        lineHeight = 30.sp
    ),
    title03_R = TextStyle(
        fontFamily = withSuhyeonFontRegular,
        fontSize = 20.sp,
        fontWeight = FontWeight(400),
        lineHeight = 30.sp
    ),
    body01_B = TextStyle(
        fontFamily = withSuhyeonFontBold,
        fontSize = 18.sp,
        fontWeight = FontWeight(700),
        lineHeight = 28.sp
    ),
    body01_SB = TextStyle(
        fontFamily = withSuhyeonFontSemiBold,
        fontSize = 18.sp,
        fontWeight = FontWeight(600),
        lineHeight = 28.sp
    ),
    body01_R = TextStyle(
        fontFamily = withSuhyeonFontRegular,
        fontSize = 18.sp,
        fontWeight = FontWeight(400),
        lineHeight = 28.sp
    ),
    body02_B = TextStyle(
        fontFamily = withSuhyeonFontBold,
        fontSize = 16.sp,
        fontWeight = FontWeight(700),
        lineHeight = 24.sp
    ),
    body02_SB = TextStyle(
        fontFamily = withSuhyeonFontSemiBold,
        fontSize = 16.sp,
        fontWeight = FontWeight(600),
        lineHeight = 24.sp
    ),
    body02_R = TextStyle(
        fontFamily = withSuhyeonFontRegular,
        fontSize = 16.sp,
        fontWeight = FontWeight(400),
        lineHeight = 24.sp
    ),
    body03_B = TextStyle(
        fontFamily = withSuhyeonFontBold,
        fontSize = 14.sp,
        fontWeight = FontWeight(700),
        lineHeight = 22.sp
    ),
    body03_SB = TextStyle(
        fontFamily = withSuhyeonFontSemiBold,
        fontSize = 14.sp,
        fontWeight = FontWeight(600),
        lineHeight = 22.sp
    ),
    body03_R = TextStyle(
        fontFamily = withSuhyeonFontRegular,
        fontSize = 14.sp,
        fontWeight = FontWeight(400),
        lineHeight = 22.sp
    ),
    caption01_B = TextStyle(
        fontFamily = withSuhyeonFontBold,
        fontSize = 12.sp,
        fontWeight = FontWeight(700),
        lineHeight = 18.sp
    ),
    caption01_SB = TextStyle(
        fontFamily = withSuhyeonFontSemiBold,
        fontSize = 12.sp,
        fontWeight = FontWeight(600),
        lineHeight = 18.sp
    ),
    caption01_R = TextStyle(
        fontFamily = withSuhyeonFontRegular,
        fontSize = 12.sp,
        fontWeight = FontWeight(400),
        lineHeight = 18.sp
    ),
    caption02_R = TextStyle(
        fontFamily = withSuhyeonFontRegular,
        fontSize = 10.sp,
        fontWeight = FontWeight(400),
        lineHeight = 16.sp
    ),
    caption02_SB = TextStyle(
        fontFamily = withSuhyeonFontSemiBold,
        fontSize = 10.sp,
        fontWeight = FontWeight(600),
        lineHeight = 16.sp
    ),
    caption02_B = TextStyle(
        fontFamily = withSuhyeonFontBold,
        fontSize = 10.sp,
        fontWeight = FontWeight(700),
        lineHeight = 16.sp
    )
)

val LocalWithSuhyeonTypographyProvider = staticCompositionLocalOf { defaultWithSuhyeonTypography }
