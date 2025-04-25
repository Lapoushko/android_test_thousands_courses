package com.lapoushko.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.lapoushko.ui.R

val fontFamily = FontFamily(
    Font(
        R.font.roboto, FontWeight.Black
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontFamily = fontFamily,
        fontSize = 14.sp,
        color = White
    ),
    titleSmall = TextStyle(
        fontFamily = fontFamily,
        fontSize = 28.sp,
        color = White
    )
)