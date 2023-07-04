package org.newsReader.com.ui.views.newsdetail


import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.placeholder.PlaceholderPlugin
import org.koin.androidx.compose.getViewModel
import org.newsReader.com.ui.composables.general.DefaultButton
import org.newsReader.com.ui.composables.general.header.TopTitle
import org.newsReader.com.ui.style.Size
import org.newsReader.com.ui.style.Size.Companion.Medium
import org.newsReader.com.ui.style.Typography.Companion.H1
import org.newsReader.com.ui.style.Typography.Companion.H2
import org.newsReader.com.viewmodels.DataViewModel
import org.newsReader.com.R
import org.newsReader.com.models.local.News
import org.newsReader.com.utils.openLink


@Composable
fun NewsDetailView(
    navController: NavHostController,
    dataViewModel: DataViewModel = getViewModel(),
) {
    val scrollState = rememberScrollState(0)
    val news = dataViewModel.livedataNews.collectAsState().value



    Column(modifier = Modifier.padding(Size.ViewPadding).verticalScroll(scrollState).padding(top= Medium),
    horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.spacedBy(Medium)) {

        TopTitle(text= stringResource(R.string.news_title) + news?.author?.let {authorName -> stringResource(R.string.from_author,authorName)},
            titleStyle = H1,
            backButtonClick = {navController.popBackStack()})

        NewsDetail(news = news)

    }

}




@Composable
fun NewsDetail(news: News?) {
    val context = LocalContext.current
    var error by remember { mutableStateOf("") }

    GlideImage(
        imageModel = { news?.urlToImage?:"" },
        imageOptions = ImageOptions(
            contentScale = ContentScale.FillWidth,
        ),
        component = rememberImageComponent {
            +PlaceholderPlugin.Loading(painterResource(id = R.drawable.wallpaper))
            +PlaceholderPlugin.Failure(painterResource(id = R.drawable.wallpaper))
        },
        modifier = Modifier
            .fillMaxWidth(0.3f)
            .aspectRatio(1f)
    )

    Text(text =  news?.title?:"",style=H2, modifier = Modifier.padding(Size.ViewPadding))

    Text(text = news?.content?:"", modifier = Modifier.padding(Size.ViewPadding))

    DefaultButton(text= stringResource(R.string.button_show_article), onClick = {
        try {
            openLink(context, Uri.parse(news?.url))
        }catch ( e: Exception) {
            error = e.message.toString()
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }, modifier = Modifier.padding(Size.ViewPadding), isDisabled = news?.url.isNullOrBlank())
}