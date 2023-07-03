package org.newsReader.com.data.local.converters


import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.newsReader.com.models.local.Source
import java.lang.reflect.Type
import java.util.*


object Converters {


    /** Timestamp Converters **/
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }


    /** Source Converters **/
    @TypeConverter
    fun fromSource(source: Source?): String? {
        val gson = Gson()
        return gson.toJson(source)
    }

    @TypeConverter
    fun toSource(source: String?): Source? {
        val listType: Type = object : TypeToken<Source?>() {}.type
        return Gson().fromJson(source, listType)
    }
}