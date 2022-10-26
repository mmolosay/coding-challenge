package com.leverx.challenge.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Layout for full screens.
 *
 * It will draw [content] on filled surface and take max size with insets for status bars,
 * but not for navigation bars.
 */
@Composable
fun Screen(
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier.statusBarsPadding()) {
            content()
        }
    }
}