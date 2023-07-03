package org.newsReader.com.data

import org.json.JSONObject
import retrofit2.Response


abstract class BaseDataSource {

    //todo: check if necesssary
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): DataResult<T> {
        try {
            val response = call()
            val body = response.body()
            var header = response.headers()
            if (response.isSuccessful) {
                if (body != null) {
                    var followingPage = header["x-wp-totalpages"]
                    return DataResult.success(body, followingPage ?: "")
                }
            }
            return try {
                val jObjError = JSONObject(response.errorBody()!!.string())
                error(
                    message = "${response.code()} ${response.message()}",
                    errorBody = jObjError
                )
            } catch (e: java.lang.Exception) {
                error(
                    message = " ${response.code()} ${response.message()}",
                )
            }
        } catch (e: Exception) {
            return error(message = (e.message ?: e.toString()))
        }
    }

    private fun <T> error(
        data: T? = null,
        message: String,
        errorBody: JSONObject? = null
    ): DataResult<T> {
        return DataResult.error(
            errorBody = errorBody,
            data = data,
            message = "Network call has failed for a following reason: $message"
        )
    }
}