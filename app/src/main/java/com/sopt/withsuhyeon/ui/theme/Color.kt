package com.sopt.withsuhyeon.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple25 = Color(0xFFFAFAFF)
val Purple50 = Color(0xFFF2F2FF)
val Purple100 = Color(0xFFDCDDFF)
val Purple200 = Color(0xFFC5C6FD)
val Purple300 = Color(0xFF9899F9)
val Purple400 = Color(0xFF7072F2)
val Purple500 = Color(0xFF5053E9)
val Purple600 = Color(0xFF383BDD)
val Purple700 = Color(0xFF272ACD)
val Purple800 = Color(0xFF1B1DB8)
val Purple900 = Color(0xFF1315A0)
val Purple950 = Color(0xFF0E1087)

val Grey25 = Color(0xFFFCFCFD)
val Grey50 = Color(0xFFF9FAFB)
val Grey100 = Color(0xFFF3F4F6)
val Grey200 = Color(0xFFE5E7EB)
val Grey300 = Color(0xFFD2D6DB)
val Grey400 = Color(0xFF9DA4AE)
val Grey500 = Color(0xFF6C737F)
val Grey600 = Color(0xFF4D5761)
val Grey700 = Color(0xFF384250)
val Grey800 = Color(0xFF1F2A37)
val Grey900 = Color(0xFF111927)
val Grey950 = Color(0xFF0D121C)

val Black = Color(0xFF000000)
val Black50 = Color(0x4D000000)
val White = Color(0xFFFFFFFF)

val Red01 = Color(0xFFFF5747)

@Immutable
data class WithSuhyeonColors(
    val Purple25: Color,
    val Purple50: Color,
    val Purple100: Color,
    val Purple200: Color,
    val Purple300: Color,
    val Purple400: Color,
    val Purple500: Color,
    val Purple600: Color,
    val Purple700: Color,
    val Purple800: Color,
    val Purple900: Color,
    val Purple950: Color,

    val Grey25: Color,
    val Grey50: Color,
    val Grey100: Color,
    val Grey200: Color,
    val Grey300: Color,
    val Grey400: Color,
    val Grey500: Color,
    val Grey600: Color,
    val Grey700: Color,
    val Grey800: Color,
    val Grey900: Color,
    val Grey950: Color,

    val Black: Color,
    val Black50: Color,
    val White: Color,

    val Red01: Color
)

val defaultWithSuhyeonColors = WithSuhyeonColors(
    Purple25 = Purple25,
    Purple50 = Purple50,
    Purple100 = Purple100,
    Purple200 = Purple200,
    Purple300 = Purple300,
    Purple400 = Purple400,
    Purple500 = Purple500,
    Purple600 = Purple600,
    Purple700 = Purple700,
    Purple800 = Purple800,
    Purple900 = Purple900,
    Purple950 = Purple950,

    Grey25 = Grey25,
    Grey50 = Grey50,
    Grey100 = Grey100,
    Grey200 = Grey200,
    Grey300 = Grey300,
    Grey400 = Grey400,
    Grey500 = Grey500,
    Grey600 = Grey600,
    Grey700 = Grey700,
    Grey800 = Grey800,
    Grey900 = Grey900,
    Grey950 = Grey950,

    Black = Black,
    Black50 = Black50,
    White = White,

    Red01 = Red01
)

val LocalWithSuhyeonColorsProvider = staticCompositionLocalOf { defaultWithSuhyeonColors }

