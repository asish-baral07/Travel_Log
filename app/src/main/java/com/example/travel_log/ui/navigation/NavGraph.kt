package com.example.travel_log.ui.navigation


import androidx.compose.runtime.Composable

import androidx.compose.runtime.collectAsState

import androidx.compose.runtime.getValue

import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavType

import androidx.navigation.compose.*

import androidx.navigation.navArgument
import com.example.travel_log.ui.details_screen.DetailContent
import com.example.travel_log.ui.explore_screens.ExploreScreen
import com.example.travel_log.ui.explore_screens.ExploreViewModel
import com.example.travel_log.ui.trip_screen.TripsScreen


@Composable

fun NavGraph() {

    val navController = rememberNavController()

    val viewModel: ExploreViewModel = viewModel()

    val countries by viewModel.state.collectAsState()

    NavHost(
        navController = navController, startDestination = "discover"
    ) {
        /*
        Explore screen
        */
        composable("discover") {

            ExploreScreen (
                viewModel = viewModel,
                onCountryClick = { country -> navController.navigate("detail/${country.code}",) },
                navController
            )
        }
        /*
        Detail screen
        */
        composable(
            route = "detail/{countryCode}",
            arguments = listOf(
                navArgument("countryCode") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val countryCode =
                backStackEntry.arguments
                    ?.getString("countryCode")

            val selectedCountry = countries.find { it.code == countryCode }
            selectedCountry?.let {
                DetailContent( country = it, navController)
            }
        }
        composable("tripscreen"){
            TripsScreen(navController)
        }
    }
}
