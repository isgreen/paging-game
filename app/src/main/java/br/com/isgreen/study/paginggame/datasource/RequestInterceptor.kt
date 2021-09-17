package br.com.isgreen.study.paginggame.datasource

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by Éverdes Soares on 06/10/2021.
 */

class RequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Content-Type", "application/json")

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}