package com.leverx.challenge.ui.environment.navigation

/**
 * Navigation routes for all posible destinations.
 */
object NavRoutes {

    val Settings = "settings"

    object Home {
        val Search = "search"
        val History = "history"
    }
}

/**
 * The route for the start destination.
 */
@Suppress("unused") // receiver
val NavRoutes.Start: String
    get() = NavRoutes.Home.Search