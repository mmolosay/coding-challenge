package com.leverx.challenge.remote.model

import com.squareup.moshi.Json

data class ImagesRequest(
    val query: String,
)

data class ImagesResponse(
    @Json(name = "stat") val status: String?,
    @Json(name = "photos") val data: Data?,
    @Json(name = "photo") val images: List<Image>?,
) {

    data class Data(
        @Json(name = "page") val page: Int?,
        @Json(name = "pages") val pages: Int?,
        @Json(name = "perpage") val resultsPerPage: Int?,
        @Json(name = "total") val resultsTotal: Int?,
    )

    data class Image(
        @Json(name = "id") val id: Long?,
        @Json(name = "title") val title: String?,
        @Json(name = "secret") val secret: String?,
        @Json(name = "server") val server: Int?,
        @Json(name = "farm") val farm: Int?,
    )
}