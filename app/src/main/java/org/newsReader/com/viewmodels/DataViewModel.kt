package org.newsReader.com.viewmodels


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.newsReader.com.data.DataResult
import org.newsReader.com.data.remote.service.global.RemoteService
import org.newsReader.com.data.repositories.DataRepository
import org.newsReader.com.models.local.News
import org.newsReader.com.models.remote.NewsResponse
import org.newsReader.com.utils.pager.NewsSource
import java.util.Locale

class DataViewModel(
    val dataRepository: DataRepository,
    val remoteService: RemoteService
) : ViewModel() {

    /** Data Results API **/
    private val dataResultNewsList: MutableState<DataResult<NewsResponse>?> =
        mutableStateOf(null)


    /** Local Data **/
    val livedataNewsList: StateFlow<List<News>?> get() = _livedataNewsList
    val _livedataNewsList: MutableStateFlow<List<News>?> = MutableStateFlow(null)

    val livedataNews: StateFlow<News?> get() = _livedataNews
    val _livedataNews: MutableStateFlow<News?> = MutableStateFlow(null)

    /** Helper **/
    val isLoading: MutableState<Boolean> = mutableStateOf(false)



    /** Remote Call **/

    //this is a example to show how to request data from api without Pager
    fun getDataFromApiWithManualPaging(page: Int) {
        viewModelScope.launch {
            isLoading.value = true
            var currentLanguage = Locale.getDefault().language

            dataResultNewsList.value = dataRepository.getNewsListFromApi(page,currentLanguage)

            dataResultNewsList.value?.let {
                if (it.status == DataResult.Status.SUCCESS) {
                    it.data?.let { newsListResponse ->

                        _livedataNewsList.value = newsListResponse.newsList

                    }
                }
            }
        }
    }

    val newsList: Flow<PagingData<News>> = Pager(PagingConfig(pageSize = 6)) {
        NewsSource(remoteService)
    }.flow.cachedIn(viewModelScope)


    /** Local Call **/

    fun setNews(news: News) {
        _livedataNews.value = news
    }
}