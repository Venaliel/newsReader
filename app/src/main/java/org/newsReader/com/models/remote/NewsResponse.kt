package org.newsReader.com.models.remote

import com.google.gson.annotations.SerializedName
import org.newsReader.com.models.local.News



data class NewsResponse(
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: String?,
    @SerializedName("articles")
    val newsList: List<News>?
)


