package org.newsReader.com.ui.style

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

class Size {

    companion object {
        /** Generic Sizes **/
        val None = 0.dp
        val Small = 4.dp
        val Medium = 10.dp
        val Large = 15.dp
        val XLarge = 20.dp
        val XXLarge = 24.dp

        /** Paddings **/
        val ViewPadding = PaddingValues(horizontal = XXLarge)
        val HeaderPadding = PaddingValues(vertical =Large, horizontal = Small)
        val CardPadding = PaddingValues(vertical = Medium)


        /** Sizes **/
        val CardHeight = 100.dp

        /** Elevation **/
        val CardElevation = 5.dp



    }
}


