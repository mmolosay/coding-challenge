package com.leverx.challenge.ui.environment.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navigation
import com.leverx.challenge.ui.components.Settings
import com.leverx.challenge.ui.components.home.History
import com.leverx.challenge.ui.components.home.Home
import com.leverx.challenge.ui.components.home.Search

@Composable
fun AppNavHost(
    navController: NavHostController,
) = NavHost(
    navController = navController,
    startDestination = NavRoutes.Start
) {
    Settings(navController)

    homeGraph(navController)
}

// region Navigate

/**
 * Navigates to route from [route] producer, scoped to [NavRoutes].
 */
private fun NavController.navigateTo(
    route: NavRoutes.() -> String,
) {
    navigate(route(NavRoutes)) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

// endregion

// region Destination composables

private fun NavGraphBuilder.Settings(navController: NavController) =
    composable(NavRoutes.Settings) {
        Settings()
    }

/**
 * Nested navigation graph of 'Home' flow.
 *
 * @see NavRoutes.Home
 */
private fun NavGraphBuilder.homeGraph(navController: NavController) =
    navigation(
        startDestination = NavRoutes.Home.Search,
        route = "graph-home"
    ) {
        Search(navController)
        History(navController)
    }

private fun NavGraphBuilder.Search(
    navController: NavController,
) =
    composable(NavRoutes.Home.Search) {
        Home(navController) {
            Search()
        }
    }

private fun NavGraphBuilder.History(
    navController: NavController,
) =
    composable(NavRoutes.Home.History) {
        Home(navController) {
            History()
        }
    }

@Composable
private fun Home(
    navController: NavController,
    content: @Composable () -> Unit
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    Home(
        backStackEntry = backStackEntry,
        navigateToSearch = { navController.navigateTo { NavRoutes.Home.Search } },
        navigateToHistory = { navController.navigateTo { NavRoutes.Home.History } },
        navigateToSettings = { navController.navigateTo { Settings } },
        content = content
    )
}

// endregion