package com.leverx.challenge.remote.api

import com.leverx.challenge.remote.model.ImagesResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * [Flick API](https://www.flickr.com/services/api/)
 */
interface FlickrApiService {

    /**
     * Get images, matching specified [query].
     *
     * Authentication-less. API key is being added via `Interceptor`.
     *
     * [Endpoint documentation](https://www.flickr.com/services/api/flickr.photos.search.html)
     */
    @GET(
        "services/rest/?method=flickr.photos.search" +
            "&format=json" +
            "&nojsoncallback=1" +
            "&safe_search=1"
    )
    suspend fun getImages(
        @Query("text") query: String
    ): ImagesResponse
}