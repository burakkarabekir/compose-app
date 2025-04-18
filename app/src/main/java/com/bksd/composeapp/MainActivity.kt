package com.bksd.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bksd.composeapp.ui.theme.ComposeAppTheme
import com.bksd.composeapp.route.AppNavigation
import com.bksd.route.AppNavigator
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val navigator: AppNavigator by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeAppTheme {
                AppNavigation(
                    navigator = navigator,
                )
            }
        }
    }
}