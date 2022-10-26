package com.leverx.challenge.ui.environment

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

/**
 * Styles [content] according to specified [theme].
 */
@Composable
fun AppTheme(
    theme: Theme = Theme.DayNight,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = inferColorScheme(theme, isSystemInDarkTheme()),
        content = content
    )
}

enum class Theme {

    /** Theme with light color scheme. */
    Light,

    /** Theme with dark color scheme. */
    Dark,

    /** Either [Light] or [Dark], depending on system's 'Dark mode' setting. */
    DayNight;
}