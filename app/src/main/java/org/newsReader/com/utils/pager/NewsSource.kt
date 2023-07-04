package org.newsReader.com.utils.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.newsReader.com.data.remote.service.global.RemoteService
import org.newsReader.com.models.local.News
import retrofit2.HttpException
import java.io.IOException
import java.util.Locale

class NewsSource(  private val service: RemoteService,) : PagingSource<Int, News>() {

    override fun getRefreshKey(state: PagingState<Int, News>): Int?
    {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        return try {
            val nextPage = params.key ?: 1
            val currentLanguage = Locale.getDefault().country
            val userList = service.getNewsList(page=nextPage, pageSize = 5, sources = currentLanguage)
            val response = userList.body()?.newsList ?: emptyList()
            val nextKey = if (response.isEmpty()) {
                null
            } else {
                nextPage + 1
            }
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}