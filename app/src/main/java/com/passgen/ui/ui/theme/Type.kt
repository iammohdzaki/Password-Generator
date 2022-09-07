package com.passgen.ui.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.passgen.ui.R

val SansFonts = FontFamily(
    Font(R.font.sans_regular, FontWeight.Normal),
    Font(R.font.sans_bold, FontWeight.Bold),
    Font(R.font.sans_medium,FontWeight.Medium),
    Font(R.font.sans_thin,FontWeight.Thin),
    Font(R.font.sans_italic,FontWeight.Normal, FontStyle.Italic),
    Font(R.font.sans_medium_italic,FontWeight.Medium, FontStyle.Italic),
    Font(R.font.sans_bold_italic,FontWeight.Bold, FontStyle.Italic)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = SansFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = SansFonts,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = SansFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)