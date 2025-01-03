package com.example.chatapp.ui.theme

import android.graphics.drawable.GradientDrawable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


val Primary = Color(0xFF760094)
val Secondary = Color(0xFF150099)
val Tertiary = Color(0xFFFCE3FC)

val Gradient = Brush.linearGradient(
    listOf(Secondary, Primary)
)