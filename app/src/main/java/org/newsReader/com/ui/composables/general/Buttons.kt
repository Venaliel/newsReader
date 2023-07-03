package org.newsReader.com.ui.composables.general

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.newsReader.com.ui.style.Color.Companion.BlueVariant
import org.newsReader.com.ui.style.Color.Companion.Grey
import org.newsReader.com.ui.style.ShapeCustom.Companion.RoundedButton
import org.newsReader.com.ui.style.Typography


@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String = "",
    textStyle: TextStyle = Typography.Body1,
    backgroundColor: Color = BlueVariant,
    borderColor: Color = Color.Black,
    onClick: () -> Unit = {},
    isDisabled: Boolean = false
) {

    Button(
        onClick = onClick,
        enabled = !isDisabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            disabledBackgroundColor = Grey,
        ),
        border = BorderStroke(1.dp, if (isDisabled) Grey else borderColor),
        modifier = Modifier
            .height(50.dp)
            .then(modifier),
        shape = RoundedButton,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            disabledElevation = 0.dp
        ),
    ) {
            Text(text, style = textStyle)
    }
}


@Preview
@Composable
fun CircularButtonPreview() {
    DefaultButton(text = "LE BOUTON DE TEST")
}

