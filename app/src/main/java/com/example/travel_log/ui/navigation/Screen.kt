package com.example.travel_log.ui.navigation

sealed class Screen (val route: String) {
    data object Explore : Screen("discover")
    data object Trip: Screen("trip_screen")
    data object Setting: Screen("setting_screen")
    data object Stats : Screen("stat_Screen")
    data object Detail : Screen("detail/{countryCode}"){
        fun createRoute(countryCode: String): String{

            return "detail/$countryCode"
        }
    }
}
