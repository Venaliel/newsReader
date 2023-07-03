package org.newsReader.com.data.remote.utils


import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.newsReader.com.BuildConfig
import java.io.IOException
import java.util.*

class AuthInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        var requestBuild = chain.request()
            .newBuilder()
            .addHeader("Accept", "*/*")
            .addHeader("X-Api-Key", BuildConfig.BASE_API_KEY)


        val request: Request = requestBuild.build()

        var requestUrl = chain.request().url

        return chain.proceed(request.newBuilder().url(requestUrl).build())
    }
}