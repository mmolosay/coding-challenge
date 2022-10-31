package com.leverx.challenge.remote.interceptor

import okhttp3.Interceptor

/**
 * Various [Interceptor]s.
 */
object Interceptors {

    // region API key

    /**
     * Interceptor for adding [apiKey] as query parameter.
     */
    fun FlickrApiKeyInterceptor(apiKey: String): Interceptor =
        QueryParameterInterceptor(
            queryParameter = "api_key",
            value = apiKey,
        )

    // endregion
}