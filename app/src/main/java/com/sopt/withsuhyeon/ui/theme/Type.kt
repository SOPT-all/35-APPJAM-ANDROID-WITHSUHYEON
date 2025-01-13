package com.sopt.withsuhyeon.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.sopt.withsuhyeon.R


val withSuhyeonFont = FontFamily(Font(R.font.suit))

val lineHeightStyle = LineHeightStyle(
    alignment = LineHeightStyle.Alignment.Center,
    trim = LineHeightStyle.Trim.None
)

@Immutable
data class WithSuhyeonTypography(
    val heading01_B: TextStyle,
    val heading01_SB: TextStyle,
    val heading01_R: TextStyle,
    val heading02_B: TextStyle,
    val heading02_SB: TextStyle,
    val heading02_R: TextStyle,
    val heading03_B: TextStyle,
    val heading03_SB: TextStyle,
    val heading03_R: TextStyle,
    val title01_B: TextStyle,
    val title01_SB: TextStyle,
    val title01_R: TextStyle,
    val title02_B: TextStyle,
    val title02_SB: TextStyle,
    val title02_R: TextStyle,
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
        fontFamily = withSuhyeonFont,
        fontSize = 32.sp,
        fontWeight = FontWeight(700),
        lineHeight = 44.sp,
        lineHeightStyle = lineHeightStyle
    ),
    heading01_SB = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 32.sp,
        fontWeight = FontWeight(600),
        lineHeight = 44.sp,
        lineHeightStyle = lineHeightStyle
    ),
    heading01_R = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 32.sp,
        fontWeight = FontWeight(400),
        lineHeight = 44.sp,
        lineHeightStyle = lineHeightStyle
    ),
    heading02_B = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 28.sp,
        fontWeight = FontWeight(700),
        lineHeight = 40.sp,
        lineHeightStyle = lineHeightStyle
    ),
    heading02_SB = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 28.sp,
        fontWeight = FontWeight(600),
        lineHeight = 40.sp,
        lineHeightStyle = lineHeightStyle
    ),
    heading02_R = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 28.sp,
        fontWeight = FontWeight(400),
        lineHeight = 40.sp,
        lineHeightStyle = lineHeightStyle
    ),
    heading03_B = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 22.sp,
        fontWeight = FontWeight(700),
        lineHeight = 34.sp,
        lineHeightStyle = lineHeightStyle
    ),
    heading03_SB = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 22.sp,
        fontWeight = FontWeight(600),
        lineHeight = 34.sp,
        lineHeightStyle = lineHeightStyle
    ),
    heading03_R = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 22.sp,
        fontWeight = FontWeight(400),
        lineHeight = 34.sp,
        lineHeightStyle = lineHeightStyle
    ),
    title01_B = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 24.sp,
        fontWeight = FontWeight(700),
        lineHeight = 36.sp,
        lineHeightStyle = lineHeightStyle
    ),
    title01_SB = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 24.sp,
        fontWeight = FontWeight(600),
        lineHeight = 36.sp,
        lineHeightStyle = lineHeightStyle
    ),
    title01_R = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 24.sp,
        fontWeight = FontWeight(400),
        lineHeight = 36.sp,
        lineHeightStyle = lineHeightStyle
    ),
    title02_B = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 20.sp,
        fontWeight = FontWeight(700),
        lineHeight = 30.sp,
        lineHeightStyle = lineHeightStyle
    ),
    title02_SB = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 20.sp,
        fontWeight = FontWeight(600),
        lineHeight = 30.sp,
        // lineHeightStyle = lineHeightStyle
    ),
    title02_R = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 20.sp,
        fontWeight = FontWeight(400),
        lineHeight = 30.sp,
        lineHeightStyle = lineHeightStyle
    ),
    body01_B = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 18.sp,
        fontWeight = FontWeight(700),
        lineHeight = 28.sp,
        lineHeightStyle = lineHeightStyle
    ),
    body01_SB = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 18.sp,
        fontWeight = FontWeight(600),
        lineHeight = 28.sp,
        lineHeightStyle = lineHeightStyle
    ),
    body01_R = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 18.sp,
        fontWeight = FontWeight(400),
        lineHeight = 28.sp,
        lineHeightStyle = lineHeightStyle
    ),
    body02_B = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 16.sp,
        fontWeight = FontWeight(700),
        lineHeight = 24.sp,
        lineHeightStyle = lineHeightStyle
    ),
    body02_SB = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 16.sp,
        fontWeight = FontWeight(600),
        lineHeight = 24.sp,
        lineHeightStyle = lineHeightStyle
    ),
    body02_R = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 16.sp,
        fontWeight = FontWeight(400),
        lineHeight = 24.sp,
        lineHeightStyle = lineHeightStyle
    ),
    body03_B = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 14.sp,
        fontWeight = FontWeight(700),
        lineHeight = 22.sp,
        lineHeightStyle = lineHeightStyle
    ),
    body03_SB = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 14.sp,
        fontWeight = FontWeight(600),
        lineHeight = 22.sp,
        lineHeightStyle = lineHeightStyle
    ),
    body03_R = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 14.sp,
        fontWeight = FontWeight(400),
        lineHeight = 22.sp,
        lineHeightStyle = lineHeightStyle
    ),
    caption01_B = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 12.sp,
        fontWeight = FontWeight(700),
        lineHeight = 18.sp,
        lineHeightStyle = lineHeightStyle
    ),
    caption01_SB = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 12.sp,
        fontWeight = FontWeight(600),
        lineHeight = 18.sp,
        lineHeightStyle = lineHeightStyle
    ),
    caption01_R = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 12.sp,
        fontWeight = FontWeight(400),
        lineHeight = 18.sp,
        lineHeightStyle = lineHeightStyle
    ),
    caption02_R = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 10.sp,
        fontWeight = FontWeight(400),
        lineHeight = 16.sp,
        lineHeightStyle = lineHeightStyle
    ),
    caption02_SB = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 10.sp,
        fontWeight = FontWeight(600),
        lineHeight = 16.sp,
        lineHeightStyle = lineHeightStyle
    ),
    caption02_B = TextStyle(
        fontFamily = withSuhyeonFont,
        fontSize = 10.sp,
        fontWeight = FontWeight(700),
        lineHeight = 16.sp,
        lineHeightStyle = lineHeightStyle
    )
)

val LocalWithSuhyeonTypographyProvider = staticCompositionLocalOf { defaultWithSuhyeonTypography }
