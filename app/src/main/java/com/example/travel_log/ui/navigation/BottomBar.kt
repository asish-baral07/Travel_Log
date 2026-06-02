package com.example.travel_log.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavController) {

    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == Screen.Explore.route,
            onClick = {navController.navigate(Screen.Explore.route)},
            icon = {
                Icon(Icons.Outlined.Home, null)
            },
            label = {
                Text("Explore")
            }
        )

        NavigationBarItem(
            selected = currentRoute == Screen.Trip.route,
            onClick = {navController.navigate(Screen.Trip.route)},
            icon = {
                Icon(Icons.AutoMirrored.Outlined.List, null)
            },
            label = {
                Text("Trips")
            }
        )

        NavigationBarItem(
            selected = currentRoute == Screen.Stats.route,
            onClick = { navController.navigate(Screen.Stats.route)},
            icon = {
                Icon(Icons.Outlined.Analytics, null)
            },
            label = {
                Text("Stats")
            }
        )

        NavigationBarItem(
            selected = currentRoute == Screen.Setting.route,
            onClick = { navController.navigate(Screen.Setting.route)},
            icon = {
                Icon(Icons.Outlined.Settings, null)
            },
            label = {
                Text("Settings")
            }
        )
    }
}