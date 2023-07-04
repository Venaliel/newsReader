package org.newsReader.com.data.repositories

import org.newsReader.com.data.BaseDataSource
import org.newsReader.com.data.local.Database
import org.newsReader.com.data.remote.service.global.RemoteService
import org.newsReader.com.models.local.News

class DataRepository(
    val database: Database,
    val remoteService: RemoteService
) : BaseDataSource() {


    /** Remote **/
    suspend fun getNewsListFromApi(page: Int, currentCountry: String) = getResult {
        remoteService.getNewsList(
            page = page,
            pageSize = 50,
            sources = currentCountry
    ) }


    /** Local **/


    fun saveLocalNews(item: News) =
        database.newsDao().upsertNews(listOf(item))

    fun getLocalFavoriteNews(): List<News> =
        database.newsDao().getListNews()

    fun deleteLocalNews(id: String) =
        database.newsDao().deleteNews(id)
}