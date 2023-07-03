package org.newsReader.com.ui.style

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.compose.material.Shapes

class ShapeCustom {

    companion object {
        val RoundedNone = 0.dp
        val RoundedExtraSmall = 10.dp
        val RoundedMedium = 18.dp
        val RoundedLarge = 25.dp

        val Shapes = Shapes(
            small = RoundedCornerShape(RoundedExtraSmall),
            medium = RoundedCornerShape(RoundedMedium),
            large = RoundedCornerShape(RoundedLarge)
        )


        val RoundedCard = RoundedCornerShape(RoundedMedium)

        val RoundedButton = RoundedCornerShape(RoundedLarge)


        val RoundedImage = RoundedCornerShape(RoundedExtraSmall)
    }
}

