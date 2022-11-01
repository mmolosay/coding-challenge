package com.leverx.challenge.model

import androidx.compose.runtime.Immutable

/**
 * @see com.leverx.challenge.domain.model.ViewedImage
 */
@Immutable
data class ViewedImage(
    val id: Long,
    val url: String?,
    val title: String?,
)