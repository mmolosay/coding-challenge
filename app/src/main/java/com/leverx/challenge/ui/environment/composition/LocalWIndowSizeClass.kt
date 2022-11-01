package com.leverx.challenge.ui.environment.composition

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

/**
 * `CompositionLocal` of [WindowSizeClass].
 * Must be initialized at the very begining of UI hierarchy.
 */
val LocalWindowSizeClass =
    staticCompositionLocalOf<WindowSizeClass> {
        error("LocalWindowSizeClass was not initialized")
    }

/**
 * Utility for obtaining current values of [LocalWindowSizeClass].
 */
object WindowSize {

    val widthClass: WindowWidthSizeClass
        @Composable
        get() = LocalWindowSizeClass.current.widthSizeClass

    val heightClass: WindowHeightSizeClass
        @Composable
        get() = LocalWindowSizeClass.current.heightSizeClass
}

/**
 * Various [WindowSizeClass]es for **Compose Previews only**.
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
object PreviewWindowSizeClasses {

    /** Generic phone device in portrait orientation. */
    val Phone =
        WindowSizeClass.calculateFromSize(DpSize(360.dp, 720.dp))

    /** Foldable device in unfolded state. */
    val Foldable =
        WindowSizeClass.calculateFromSize(DpSize(720.dp, 720.dp))

    /** Generic tablet device in landsape orientation. */
    val Tablet =
        WindowSizeClass.calculateFromSize(DpSize(860.dp, 480.dp))
}

