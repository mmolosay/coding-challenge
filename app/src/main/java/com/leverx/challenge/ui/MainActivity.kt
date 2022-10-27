package com.leverx.challenge.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.leverx.challenge.ui.components.Application
import com.leverx.challenge.ui.components.common.Screen
import com.leverx.challenge.ui.environment.AppTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent()
    }

    private fun setContent() {
        WindowCompat.setDecorFitsSystemWindows(window, false) // overlap status bar
        setContent {
            AppTheme {
                Screen {
                    Application()
                }
            }
        }
    }
}