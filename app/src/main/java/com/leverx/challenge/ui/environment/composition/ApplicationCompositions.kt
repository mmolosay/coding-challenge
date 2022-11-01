package com.leverx.challenge.ui.environment.composition

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

/**
 * Provides specified values to appropriate 'CompositionLocal's.
 */
@Composable
fun AppCompostionLocalProvider(
    windowSizeClass: WindowSizeClass,
    content: @Composable () -> Unit
) =
    CompositionLocalProvider(
        LocalWindowSizeClass provides windowSizeClass,
        content = content
    )

/**
 * [AppCompostionLocalProvider] with default parameter values.
 * Should be used **in Compose Previews only!**.
 */
@Composable
fun AppPreviewCompositionLocalProvider(
    windowSizeClass: WindowSizeClass = PreviewWindowSizeClasses.Phone,
    content: @Composable () -> Unit
) =
    AppCompostionLocalProvider(
        windowSizeClass = windowSizeClass,
        content = content
    )