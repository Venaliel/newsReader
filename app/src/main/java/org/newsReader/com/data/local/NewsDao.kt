package org.newsReader.com.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import org.newsReader.com.models.local.News


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: News)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: List<News>)

    @Query("SELECT * FROM news ORDER BY publishedAt DESC LIMIT 10")
    fun getListNews(): List<News>

    @Query("DELETE FROM news")
    fun deleteNews()

    @Query("DELETE FROM news WHERE id = :newsId")
    fun deleteNews(newsId: Int)

    @Transaction
    fun upsertNews(news: List<News>) {
        deleteNews()
        insert(news)
    }

}
