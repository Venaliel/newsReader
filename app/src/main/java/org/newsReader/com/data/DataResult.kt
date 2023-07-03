package org.newsReader.com.data

import org.json.JSONObject

data class DataResult<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val errorBody: JSONObject? = null
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
    }

    companion object {
        fun <T> success(data: T, message: String): DataResult<T> {
            return DataResult(Status.SUCCESS, data, message)
        }

        fun <T> error(
            message: String,
            data: T? = null,
            errorBody: JSONObject? = null
        ): DataResult<T> {
            return  DataResult(Status.ERROR, data, message, errorBody)
        }

        fun <T> loading(data: T? = null): DataResult<T> {
            return DataResult(Status.LOADING, data, null)
        }
    }
}