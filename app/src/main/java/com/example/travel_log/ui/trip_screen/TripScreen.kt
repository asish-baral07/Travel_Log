package com.example.travel_log.ui.trip_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.travel_log.ui.component.TripCard
import com.example.travel_log.ui.navigation.BottomBar

@Composable
fun MyTripsScreen(
    navController: NavController,
    // ViewModel provides Room DB data
    viewModel: TripsViewModel = hiltViewModel() // Gets ViewModel automatically from Hilt
) {
    //Collect planned trips from StateFlow
    val plannedTrips by viewModel.plannedTrips.collectAsState()
    // Collect wishlist trips
    val wishlistTrips by viewModel.wishlistTrips.collectAsState()
    val visitedTrips by viewModel.visitedTrips.collectAsState()
    // Dynamic counts
    val plannedCount by viewModel.plannedCount.collectAsState()
    val wishlistCount by viewModel.wishlistCount.collectAsState()
    val visitedCount by viewModel.visitedCount.collectAsState()
    // Current selected tab
    var selectedTab by remember { mutableIntStateOf(0) }

    // Screen layout
    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { paddingValues -> // prevents UI overlapping
        Column(
            modifier = Modifier // modifier customizes UI
                .fillMaxSize() // occupy full screen
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Screen title
            Text(
                text = "My Trips",
                style = MaterialTheme.typography.headlineLarge, // apply predefined typography style
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Top statistics card
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFF006D5B),
                        RoundedCornerShape(20.dp)
                    )
                    .padding(vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,

            ) {

                // Planned count
                StatsItem(
                    count = plannedCount,
                    label = "Planned"
                )
                // Wishlist count
                StatsItem(
                    count = wishlistCount,
                    label = "Wishlist"
                )
                // visited count
                StatsItem(
                    count = visitedCount,
                    label = "Visited"
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            //  Create Tabs layout
            TabRow(
                selectedTabIndex = selectedTab, // use for current selected tab
                containerColor = Color.Transparent
            ) {
                Tab(
                    selected = selectedTab == 0, // Checks if Planned tab selected
                    onClick = {
                        selectedTab = 0
                    },
                    text = {
                        Text("Planned")
                    }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = {
                        selectedTab = 1
                    },
                    text = {
                        Text("Wishlist")
                    }
                )
                Tab(
                    selected = selectedTab == 2,
                    onClick = {
                        selectedTab = 2
                    },
                    text = {
                        Text("Visited")
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
           ) {
                when(selectedTab){

                    // Planned Tab
                    0 -> {  // Shows planned trips

                        items(plannedTrips){ trip -> // current item

                            TripCard(
                                trip = trip,
                                buttonText = "Mark as Visited",
                                onButtonClick = {
                                    viewModel.moveToVisited(trip)
                                }
                            )
                        }
                    }

                    // Wishlist Tab
                    1 -> {

                        items(wishlistTrips){ trip ->

                            TripCard(
                                trip = trip,
                                buttonText = "Plan Trip",
                                onButtonClick = {
                                    viewModel.moveToPlanned(trip)
                                }
                            )
                        }
                    }

                    // Visited Tab
                    2 -> {

                        items(visitedTrips){ trip ->

                            TripCard(
                                trip = trip,
                                buttonText = "",
                                onButtonClick = { }
                            )
                        }
                    }
                }
            }
        }
    }
}
// Reusable statistics item
@Composable
fun StatsItem( count: Int, label: String ) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Count text
        Text(
            text = count.toString(),
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        // Label text
        Text(
            text = label,
            color = Color.White
        )
    }
}
