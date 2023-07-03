package org.newsReader.com.ui.views.newslist


import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import org.koin.androidx.compose.getViewModel
import org.newsReader.com.models.local.News
import org.newsReader.com.ui.composables.general.header.TopTitle
import org.newsReader.com.ui.composables.specific.widget.NewsWidget
import org.newsReader.com.ui.style.Size
import org.newsReader.com.ui.style.Typography.Companion.H1
import org.newsReader.com.utils.constants.NavigationConstants
import org.newsReader.com.viewmodels.DataViewModel
import org.newsReader.com.R

@Composable
fun NewsListView(
    navController: NavHostController,
    dataViewModel: DataViewModel = getViewModel(),
) {

    val context = LocalContext.current
    var basicError = stringResource(R.string.basic_error)

    val newsListItems: LazyPagingItems<News> =  dataViewModel.newsList.collectAsLazyPagingItems()


    LazyColumn(modifier =
    Modifier.padding(Size.ViewPadding)
        .padding(top= Size.Medium)
        .testTag("NewsListView") // Used for UI testing
    ){

        item{
            TopTitle(text= stringResource(R.string.news_list_title), titleStyle = H1.copy(fontSize=23.sp))
        }

        items(newsListItems) { item ->
            item?.let { news ->
                NewsWidget(news) {
                    dataViewModel.setNews(news)
                  navController.navigate(NavigationConstants.NEWS_DETAIL)
                }
            }
        }



        newsListItems.apply {
            when {
                //todo: Add loading state
                loadState.append is LoadState.Error -> {
                    Toast.makeText(context,basicError, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}


