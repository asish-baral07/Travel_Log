package com.example.travel_log.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavController) {

    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == "discover",
            onClick = {navController.navigate("discover")},
            icon = {
                Icon(Icons.Outlined.Home, null)
            },
            label = {
                Text("Explore")
            }
        )

        NavigationBarItem(
            selected = currentRoute == "tripscreen",
            onClick = {navController.navigate("tripscreen")},
            icon = {
                Icon(Icons.Outlined.List, null)
            },
            label = {
                Text("Trips")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(Icons.Outlined.Analytics, null)
            },
            label = {
                Text("Stats")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(Icons.Outlined.Settings, null)
            },
            label = {
                Text("Settings")
            }
        )
    }
}