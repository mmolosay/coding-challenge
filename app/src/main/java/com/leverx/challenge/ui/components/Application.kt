package com.leverx.challenge.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.leverx.challenge.ui.components.common.Screen
import com.leverx.challenge.ui.environment.AppTheme
import com.leverx.challenge.ui.environment.navigation.AppNavHost

// region Previews

@Composable
@Preview
private fun Application_Preview() {
    AppTheme {
        Screen {
            Application()
        }
    }
}

// endregion

/**
 * Root component of whole application's UI layout.
 */
@Composable
fun Application() {
    val navController = rememberNavController()

    CompositionLocalProvider {
        AppNavHost(navController = navController)
    }
}