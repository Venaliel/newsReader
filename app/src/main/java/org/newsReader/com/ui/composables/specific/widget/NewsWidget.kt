package org.newsReader.com.ui.composables.specific.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.placeholder.PlaceholderPlugin
import org.newsReader.com.R
import org.newsReader.com.models.local.News
import org.newsReader.com.ui.style.Color
import org.newsReader.com.ui.style.ShapeCustom.Companion.RoundedImage
import org.newsReader.com.ui.style.Size


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsWidget(news: News,onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .padding(Size.CardPadding)
            .height(Size.CardHeight),
        elevation = Size.Small,
        onClick = {
            onClick.invoke()
        }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(Size.ViewPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {

            GlideImage(
                imageModel = { news.urlToImage?:"" },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                ),
                component = rememberImageComponent {
                    +PlaceholderPlugin.Loading(painterResource(id = R.drawable.news))
                    +PlaceholderPlugin.Failure(painterResource(id = R.drawable.news))
                },
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .clip(RoundedImage),
            )

            Column(
                Modifier.weight(4f).padding(start= Size.XXLarge),
                verticalArrangement = Arrangement.spacedBy(Size.Medium)
            ) {
                Text(
                    text = news.title?:"",
                    style = org.newsReader.com.ui.style.Typography.H2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = news.author?:"",
                    style = org.newsReader.com.ui.style.Typography.Body1,
                )
            }

            // todo: add chevron

        }

    }
}