package com.example.todocomposeapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.todocomposeapp.R

val AppFont = FontFamily(Font(R.font.roboto_regular), Font(R.font.roboto_medium, FontWeight.Medium))

val AppTypography = Typography(
    displayLarge = TextStyle(fontFamily = AppFont, fontWeight = FontWeight.Bold, fontSize = 34.sp),
    titleLarge = TextStyle(fontFamily = AppFont, fontWeight = FontWeight.Medium, fontSize = 24.sp),
    titleMedium = TextStyle(fontFamily = AppFont, fontWeight = FontWeight.Medium, fontSize = 20.sp),
    bodyLarge = TextStyle(fontFamily = AppFont, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    labelSmall = TextStyle(fontFamily = AppFont, fontWeight = FontWeight.Medium, fontSize = 11.sp)
)
