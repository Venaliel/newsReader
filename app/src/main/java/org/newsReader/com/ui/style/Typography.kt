package org.newsReader.com.ui.style

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.newsReader.com.R
import androidx.compose.material.Typography as materialTypography

class Typography {

    companion object {


        /** Fonts **/
        private val CIRCULAR_STD_MEDIUM = FontFamily(Font(R.font.circular_std_medium))


        /** Typography **/
        val H1 = TextStyle(
            fontFamily = CIRCULAR_STD_MEDIUM,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 19.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.66.sp
        )
        val H2 = TextStyle(
            fontFamily = CIRCULAR_STD_MEDIUM,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.66.sp
        )
        val Body1 = TextStyle(
            fontFamily = CIRCULAR_STD_MEDIUM,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 24.sp
        )


        /** Override Material Typography **/
        val Typography = materialTypography(
            h1 = H1,
            h2 = H2,
            body1 = Body1
        )
    }
}