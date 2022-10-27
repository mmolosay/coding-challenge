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
import androidx.navigation.compose.rememberNavController
import com.leverx.challenge.ui.components.Settings
import com.leverx.challenge.ui.components.home.History
import com.leverx.challenge.ui.components.home.Home
import com.leverx.challenge.ui.components.home.Search
import com.leverx.challenge.viewmodel.SearchViewModel

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
        startDestination = NavRoutes.Home.route
    ) {
        Settings(navController)
        Home(navController)
    }

private fun NavGraphBuilder.Settings(
    navController: NavController
) =
    composable(NavRoutes.Settings) {
        Settings()
    }

/**
 * Not really a nested navigation graph, but a destination with their own [NavHost]
 * and sub-destinations.
 *
 * @param navController application's navigation controller.
 *
 * @see NavRoutes.Home
 */
private fun NavGraphBuilder.Home(
    navController: NavController,
) =
    composable(NavRoutes.Home.route) {
        val homeNavController = rememberNavController() // individual one
        val backStackEntry by homeNavController.currentBackStackEntryAsState()
        Home(
            currentRoute = backStackEntry?.destination?.route,
            navigateToSettings = { navController.navigateTo { Settings } },
            navigateToSearch = { homeNavController.navigateTo { NavRoutes.Home.Search } },
            navigateToHistory = { homeNavController.navigateTo { NavRoutes.Home.History } },
        ) {
            HomeNavHost(navController = homeNavController)
        }
    }

// endregion

// region Home host

@Composable
private fun HomeNavHost(
    navController: NavHostController,
) =
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.Search,
        route = NavRoutes.Home.route,
    ) {
        Search(navController)
        History(navController)
    }

private fun NavGraphBuilder.Search(
    navController: NavController,
) =
    composable(NavRoutes.Home.Search) {
        val vm = SearchViewModel() // TODO: use DI
        Search(
            vm = vm,
        )
    }

private fun NavGraphBuilder.History(
    navController: NavController,
) =
    composable(NavRoutes.Home.History) {
        History()
    }

// endregion

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