package com.example.travel_log.ui.statsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.travel_log.ui.component.StatCard
import com.example.travel_log.ui.navigation.BottomBar
import com.example.travel_log.ui.trip_screen.TripsViewModel

@Composable
fun StatsScreen(
    viewModel: TripsViewModel, // provides all the trip data from TripsViewModel
    navController: NavController
) {
    val plannedTrips by viewModel.plannedTrips.collectAsState()
    val wishlistTrips by viewModel.wishlistTrips.collectAsState()
    val visitedTrips by viewModel.visitedTrips.collectAsState()

    // Total trip calculation
    val totalTrips = plannedTrips.size + wishlistTrips.size + visitedTrips.size

    val continentsCovered =
        visitedTrips.map { it.region } // converting a list of trip object to list of regions
                    .distinct().size // distinct used for remove duplicates

    val progress = (visitedTrips.size / 50f).coerceIn(0f, 1f) // limits value between 0 and 1, prevents progress bar overflow

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)  // prevents content hiding behind BottomBar
                .padding(horizontal =20.dp)
                .verticalScroll(rememberScrollState())
                .padding(top = 20.dp, bottom = 100.dp)
        ) {

            Text(
                text = "Travel Stats",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // TOP ANALYTICS CARDS
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatCard(
                    title = "Visited",
                    value = visitedTrips.size.toString()
                )

                StatCard(
                    title = "Wishlist",
                    value = wishlistTrips.size.toString()
                )

                StatCard(
                    title = "Planned",
                    value = plannedTrips.size.toString()
                )
            }


            Spacer(modifier = Modifier.height(28.dp))

            // PROGRESS SECTION
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF00796B)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "Travel Goal",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "${visitedTrips.size} / 50 Countries Visited",
                        color = Color.White.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    LinearProgressIndicator(
                        progress = progress,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp),
                        color = Color.White,
                        trackColor = Color.White.copy(alpha = 0.3f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Trips Overview",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                PieChart(
                    visited = visitedTrips.size.toFloat(),
                    wishlist = wishlistTrips.size.toFloat(),
                    planned = plannedTrips.size.toFloat()
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // EXTRA ANALYTICS
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 7.dp)
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = "Travel Analytics",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    AnalyticsRow(
                        label = "Total Trips",
                        value = totalTrips.toString()
                    )

                    AnalyticsRow(
                        label = "Visited Countries",
                        value = visitedTrips.size.toString()
                    )

                    AnalyticsRow(
                        label = "Continents Covered",
                        value = continentsCovered.toString()
                    )
                }
            }


        }
    }

}
