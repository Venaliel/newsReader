package org.newsReader.com.ui.composables.general.header


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import org.newsReader.com.R
import org.newsReader.com.ui.style.Size.Companion.HeaderPadding
import org.newsReader.com.ui.style.Size.Companion.Medium
import org.newsReader.com.ui.style.Typography.Companion.H1
import org.newsReader.com.ui.style.Typography.Companion.H2

@Composable
fun TopTitle(
    modifier: Modifier = Modifier,
    backButtonClick: (() -> Unit)? = null,
    titleStyle : TextStyle = H1,
    text: String? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(HeaderPadding)
            .then(modifier),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            backButtonClick?.let {
                //todo: A better looking back button using font awesome
                Text(
                    stringResource(R.string.close_button),
                    style = H2,
                    modifier = Modifier
                        .padding(end= Medium)
                        .clickable { it() }
                )
            }
            if (text != null) {
                Text(
                    text = text,
                    style = titleStyle,
                    modifier = Modifier.padding(start = Medium)
                )
            }
        }

    }
}