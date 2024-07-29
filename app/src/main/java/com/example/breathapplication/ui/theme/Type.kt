package com.example.breathapplication.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.breathapplication.R

/** FontFamily 간단하게 사용하기 위해 정리한 부분 */
val breathFontFamily = FontFamily(
    Font(R.font.wantedsans_semibold, FontWeight.SemiBold),
    Font(R.font.wantedsans_medium, FontWeight.Medium),
)

/** 피그마에 name에 맞춰서 정리  */
object Typography2 {
    val h1 = TextStyle(
        color = Color.Black,
        fontFamily = breathFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 22.sp,
    )
    val subHead = TextStyle(
        fontFamily = breathFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp,
        lineHeight = 28.sp
    )
    val title = TextStyle(
        fontFamily = breathFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp,
    )
    val subTitle = TextStyle(
        fontFamily = breathFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        lineHeight = 22.sp
    )
    val bodyText = TextStyle(
        fontFamily = breathFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 24.sp
    )
    val body1 = TextStyle(
        fontFamily = breathFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 22.sp
    )
    val body2 = TextStyle(
        fontFamily = breathFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
    )
    val body3 = TextStyle(
        fontFamily = breathFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
    )
    val body4 = TextStyle(
        fontFamily = breathFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 10.sp,
    )
    val button = TextStyle(
        fontFamily = breathFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        lineHeight = 20.sp
    )
    val caption = TextStyle(
        fontFamily = breathFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 8.sp,
    )
}

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
)
