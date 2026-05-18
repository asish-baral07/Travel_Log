package com.example.travel_log

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.travel_log.ui.explore_screens.ExploreScreen
import com.example.travel_log.ui.navigation.NavGraph
//import com.example.travel_log.ui.navigation.NavGraph
import com.example.travel_log.ui.theme.Travel_LogTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            Travel_LogTheme {
                NavGraph()
            }
        }
    }
}



