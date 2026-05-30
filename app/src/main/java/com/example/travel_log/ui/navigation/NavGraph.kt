package com.example.travel_log.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.travel_log.ui.details_screen.DetailContent
import com.example.travel_log.ui.explore_screens.ExploreScreen
import com.example.travel_log.ui.explore_screens.ExploreViewModel
import com.example.travel_log.ui.setting_screen.SettingScreen
import com.example.travel_log.ui.statsScreen.StatsScreen
import com.example.travel_log.ui.trip_screen.MyTripsScreen
import com.example.travel_log.ui.trip_screen.TripsViewModel

@Composable
fun NavGraph(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    val navController = rememberNavController()
    val viewModel: ExploreViewModel = viewModel()
    val countries by viewModel.state.collectAsState()
    NavHost(
        navController = navController, startDestination = "discover"
    ) {
        composable(Screen.Explore.route) {
            ExploreScreen (
                viewModel = viewModel,
                onCountryClick = { country -> navController.navigate(Screen.Detail.createRoute(country.code)) },
                navController
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("countryCode") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val countryCode = backStackEntry.arguments?.getString("countryCode")

            val selectedCountry = countries.find { it.code == countryCode }
            selectedCountry?.let {
                DetailContent( country = it, navController)
            }
        }
        composable(Screen.Trip.route){
            MyTripsScreen(navController)
        }
        composable(Screen.Setting.route){
            SettingScreen(
                navController = navController,
                isDarkTheme = isDarkTheme,
                onThemeChange = onThemeChange
            )
        }
        composable(Screen.Stats.route){
            val tripsViewModel : TripsViewModel = hiltViewModel()
            StatsScreen(
                viewModel = tripsViewModel,
                navController
            )
        }
    }
}
