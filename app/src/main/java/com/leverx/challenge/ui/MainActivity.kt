package com.leverx.challenge.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.view.WindowCompat
import com.leverx.challenge.ui.components.Application
import com.leverx.challenge.ui.components.common.Screen
import com.leverx.challenge.ui.environment.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent()
    }

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    private fun setContent() {
        WindowCompat.setDecorFitsSystemWindows(window, false) // overlap status bar
        setContent {
            AppTheme {
                Screen {
                    Application(
                        windowSizeClass = calculateWindowSizeClass(this),
                    )
                }
            }
        }
    }
}