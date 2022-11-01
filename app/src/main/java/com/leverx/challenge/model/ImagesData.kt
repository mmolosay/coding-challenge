package com.leverx.challenge.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

/**
 * @see com.leverx.challenge.domain.model.ImagesData
 */
@Stable
data class ImagesData(
    val images: List<RemoteImage>?,
) {

    @Immutable
    data class RemoteImage(
        val id: Long,
        val url: String?,
        val title: String?,
    )
}