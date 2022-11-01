package com.leverx.challenge.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Appends [queryParameter] to request URL with [value] as its value.
 */
class QueryParameterInterceptor(
    private val queryParameter: String,
    private val value: String,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder()
            .addQueryParameter(queryParameter, value)
            .build()
        val request = chain.request().newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}