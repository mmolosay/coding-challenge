package com.leverx.challenge.domain.model

/*
 * It may look like RemoteImages.Image, but actually it's so called "false duplication".
 * These two components will evolve along different paths over time and for different reasons.
 */
/**
 * Represents an image user viewed (interacted with).
 */
data class ViewedImage(
    val id: Long?,
    val url: String?,
    val title: String?,
)