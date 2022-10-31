package com.leverx.challenge.ui.environment

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

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

/**
 * Returns [LocalContentColor] value with specified [alpha].
 */
@Composable
fun translucentContentColor(alpha: Float): Color =
    LocalContentColor.current.copy(alpha = alpha)

/**
 * Returns [LocalContentColor] value in its disabled state.
 */
@Composable
fun disabledContentColor(): Color =
    translucentContentColor(alpha = 0.4f)

private val lightColorScheme =
    lightColorScheme()

private val darkColorScheme =
    darkColorScheme()