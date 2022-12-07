package dev.eury.introtojetpackcompose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val Typography.bigTextField
    get() = TextStyle(
        fontSize = 30.sp,
        color = Color.White,
        textAlign = TextAlign.Center
    )
