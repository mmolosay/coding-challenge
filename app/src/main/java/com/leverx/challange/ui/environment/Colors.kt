package com.leverx.challange.ui.environment

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

/**
 * Infers appropriate [ColorScheme] for specified [theme].
 * [Theme.DayNight]'s color scheme will be resolved, using [isSystemInDarkMode].
 */
fun inferColorScheme(
    theme: Theme,
    isSystemInDarkMode: Boolean,
): ColorScheme =
    when (theme) {
        Theme.Light -> lightColorScheme
        Theme.Dark -> darkColorScheme
        Theme.DayNight -> if (isSystemInDarkMode) darkColorScheme else lightColorScheme
    }

private val lightColorScheme =
    lightColorScheme()

private val darkColorScheme =
    darkColorScheme()