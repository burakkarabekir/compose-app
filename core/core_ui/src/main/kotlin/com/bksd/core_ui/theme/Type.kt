package com.bksd.core_ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.bksd.core_ui.R

private val SourceSansPro = FontFamily(
    Font(R.font.sourcesanspro_extra_light, FontWeight.ExtraLight),
    Font(R.font.sourcesanspro_light, FontWeight.Light),
    Font(R.font.sourcesanspro_regular, FontWeight.Normal),
    Font(R.font.sourcesanspro_semi_bold, FontWeight.SemiBold),
    Font(R.font.sourcesanspro_bold, FontWeight.Bold),
    Font(R.font.sourcesanspro_black, FontWeight.Black)
)

data class AppTypography(
    val fontFamily: FontFamily = SourceSansPro,
    val color: Color = Color.Unspecified
) {
    //Heading & Title
    val heading0 = TextStyle(
        fontFamily = fontFamily,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.Bold,
        color = color
    )
    val heading1 = TextStyle(
        fontFamily = fontFamily,
        fontSize = 24.sp,
        lineHeight = 30.sp,
        fontWeight = FontWeight.Bold,
        color = color
    )
    val heading2 = TextStyle(
        fontFamily = fontFamily,
        fontSize = 18.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.Bold,
        color = color
    )
    val heading3 = TextStyle(
        fontFamily = fontFamily,
        fontSize = 18.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.SemiBold,
        color = color
    )
    val title1 = TextStyle(
        fontFamily = fontFamily,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Bold,
        color = color
    )
    val title2 = TextStyle(
        fontFamily = fontFamily,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = color
    )
    val title2Strikethrough = TextStyle(
        fontFamily = fontFamily,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Bold,
        color = color,
        textDecoration = TextDecoration.LineThrough
    )
    val title3 = TextStyle(
        fontFamily = fontFamily,
        fontSize = 12.sp,
        lineHeight = 15.sp,
        fontWeight = FontWeight.Bold,
        color = color
    )

    //Body
    val body1 = TextStyle(
        fontFamily = fontFamily,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal,
        color = color
    )
    val body2 = TextStyle(
        fontFamily = fontFamily,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        fontWeight = FontWeight.Normal,
        color = color
    )
    val number = TextStyle(
        fontFamily = fontFamily,
        fontSize = 24.sp,
        lineHeight = 30.sp,
        fontWeight = FontWeight.SemiBold,
        color = color
    )

    val buttonLarge = TextStyle(
        fontFamily = fontFamily,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Bold,
        color = color
    )
    val buttonSmall = TextStyle(
        fontFamily = fontFamily,
        fontSize = 12.sp,
        lineHeight = 15.sp,
        fontWeight = FontWeight.Bold,
        color = color
    )
    val bottomMenuText = TextStyle(
        fontFamily = fontFamily,
        fontSize = 10.sp,
        lineHeight = 12.sp,
        fontWeight = FontWeight.Normal,
        color = color
    )
    val bottomMenuSelected = TextStyle(
        fontFamily = fontFamily,
        fontSize = 10.sp,
        lineHeight = 12.sp,
        fontWeight = FontWeight.Bold,
        color = color
    )
    val filterText = TextStyle(
        fontFamily = fontFamily,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Normal,
        color = color
    )
    val filterSelected = TextStyle(
        fontFamily = fontFamily,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Bold,
        color = color
    )
    val tagText = TextStyle(
        fontFamily = fontFamily,
        fontSize = 11.sp,
        lineHeight = 14.sp,
        fontWeight = FontWeight.Bold,
        color = color
    )
    val tagTextBig = TextStyle(
        fontFamily = fontFamily,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Bold,
        color = color
    )
    val infoText = TextStyle(
        fontFamily = fontFamily,
        fontSize = 10.sp,
        lineHeight = 12.sp,
        fontWeight = FontWeight.SemiBold,
        color = color
    )
}