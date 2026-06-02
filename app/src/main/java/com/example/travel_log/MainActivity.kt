package com.example.travel_log

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat
import com.example.travel_log.ui.navigation.NavGraph
import com.example.travel_log.ui.theme.Travel_LogTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            var isDarkTheme by rememberSaveable { mutableStateOf(false) } 
            val view = LocalView.current      // Get current activity window

            /*SideEffect runs after every recomposition
            Used for system UI updates.*/
            SideEffect {
                // Controls status bar icon colors
                WindowInsetsControllerCompat(window, view)
                    .isAppearanceLightStatusBars = !isDarkTheme
            }

            Travel_LogTheme(darkTheme = isDarkTheme) {
                NavGraph(
                    isDarkTheme = isDarkTheme,
                    onThemeChange = { isDarkTheme = it }
                )
            }
        }
    }
}



