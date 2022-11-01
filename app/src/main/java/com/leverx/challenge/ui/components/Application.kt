package com.leverx.challenge.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.leverx.challenge.ui.components.common.Screen
import com.leverx.challenge.ui.components.home.Home
import com.leverx.challenge.ui.environment.AppTheme
import com.leverx.challenge.ui.environment.navigation.AppNavHost
import com.leverx.challenge.ui.environment.navigation.navigateTo

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
    val backStackEntry by navController.currentBackStackEntryAsState()

    CompositionLocalProvider {
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