package com.leverx.challenge.ui.components

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.leverx.challenge.ui.components.common.Screen
import com.leverx.challenge.ui.components.home.Home
import com.leverx.challenge.ui.environment.AppTheme
import com.leverx.challenge.ui.environment.composition.LocalWindowSizeClass
import com.leverx.challenge.ui.environment.navigation.AppNavHost
import com.leverx.challenge.ui.environment.navigation.navigateTo

// region Previews

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
@Preview
private fun Application_Preview() {
    val windowSize = DpSize(360.dp, 722.dp)
    val windowSizeClass = WindowSizeClass.calculateFromSize(windowSize)
    AppTheme {
        Screen {
            Application(
                windowSizeClass = windowSizeClass,
            )
        }
    }
}

// endregion

/**
 * Root component of whole application's UI layout.
 */
@Composable
fun Application(
    windowSizeClass: WindowSizeClass,
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    CompositionLocalProvider(
        LocalWindowSizeClass provides windowSizeClass,
    ) {
        Home(
            currentRoute = backStackEntry?.destination?.route,
            navigateToSearch = { navController.navigateTo { Search } },
            navigateToHistory = { navController.navigateTo { History } }
        ) {
            AppNavHost(
                navController = navController
            )
        }
    }
}