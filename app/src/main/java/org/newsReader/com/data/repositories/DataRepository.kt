package org.newsReader.com.data.repositories

import org.newsReader.com.data.BaseDataSource
import org.newsReader.com.data.local.Database
import org.newsReader.com.data.remote.service.global.RemoteService

class DataRepository(
    val database: Database,
    val remoteService: RemoteService
) : BaseDataSource() {


    /** Remote **/
    suspend fun getNewsListFromApi(page: Int, language: String) = getResult {
        remoteService.getNewsList(
            page = page,
            pageSize = 50,
            sources = language
    ) }


    /** Local **/

}