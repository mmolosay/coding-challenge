package com.leverx.challenge.remote.model

import com.squareup.moshi.Json

data class ImagesRequest(
    val query: String,
)

data class ImagesResponse(
    @field:Json(name = "stat") val status: String?,
    @field:Json(name = "photos") val data: Data?,
) {

    data class Data(
        @field:Json(name = "page") val page: Int?,
        @field:Json(name = "pages") val pages: Int?,
        @field:Json(name = "perpage") val resultsPerPage: Int?,
        @field:Json(name = "total") val resultsTotal: Int?,
        @field:Json(name = "photo") val images: List<Image>?,
    )

    data class Image(
        @field:Json(name = "id") val id: Long?,
        @field:Json(name = "title") val title: String?,
        @field:Json(name = "secret") val secret: String?,
        @field:Json(name = "server") val server: Int?,
        @field:Json(name = "farm") val farm: Int?,
    )
}