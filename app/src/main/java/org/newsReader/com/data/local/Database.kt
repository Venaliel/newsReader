package org.newsReader.com.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.newsReader.com.data.local.converters.Converters
import org.newsReader.com.models.local.News


@Database(
    entities = [
        News::class
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}