package org.newsReader.com.data.remote.service.global

import org.newsReader.com.models.remote.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RemoteService {

    @GET("v2/top-headlines")
    suspend fun getNewsList(
        @Query("country") sources: String?,
        @Query("pageSize") pageSize: Int?,
        @Query("page") page: Int = 1
    ): Response<NewsResponse>

}