package com.leverx.challenge.ui.environment.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.leverx.challenge.ui.components.home.History
import com.leverx.challenge.ui.components.home.Search

// region Application host

/**
 * Main [NavHost] of application.
 * Contains global destinations and nested graphs.
 */
@Composable
fun AppNavHost(
    navController: NavHostController,
) =
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Search
    ) {
        Search(navController)
        History(navController)
    }

// endregion

// region Home

private fun NavGraphBuilder.Search(
    navController: NavController,
) =
    composable(NavRoutes.Search) {
        Search()
    }

private fun NavGraphBuilder.History(
    navController: NavController,
) =
    composable(NavRoutes.History) {
        History()
    }

// endregion

// region Navigate

/**
 * Navigates to route from [route] producer, scoped to [NavRoutes].
 */
fun NavController.navigateTo(
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