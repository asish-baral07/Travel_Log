package com.example.travel_log.ui.trip_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.travel_log.ui.navigation.BottomBar

@Composable
fun TripsScreen(navController: NavController) {
    Scaffold(
        bottomBar ={ BottomBar(navController)}
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF3F8F6))
                .padding(paddingValues)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            // Header
            Text(
                text = "My Trips",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1F2D2B),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(
                text = "14 countries · 32 trips",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Stats Card
            TripStatsCard()

            Spacer(modifier = Modifier.height(20.dp))

            // Tabs
            TripTabs()

            Spacer(modifier = Modifier.height(16.dp))

            // Trips List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    TripItem(
                        title = "Tokyo Adventure",
                        subtitle = "Japan · Mar 15 - Mar 28",
                        days = "PLANNED · 12 days",
                        flagColor = Color(0xFF4FA4A0)
                    )
                }
                item {
                    TripItem(
                        title = "Italy Wine Tour",
                        subtitle = "Tuscany · May 10 - May 20",
                        days = "PLANNED · 10 days",
                        flagColor = Color(0xFFE2A16D)
                    )
                }
                item {
                    TripItem(
                        title = "NZ Road Trip",
                        subtitle = "South Island · Jul 5 - Jul 22",
                        days = "PLANNED · 17 days",
                        flagColor = Color(0xFF6AAADF)
                    )
                }
            }
        }
    }
}


@Composable
fun TripStatsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2E5B55)),
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem("14", "Visited")
            StatItem("3", "Planned")
            StatItem("28", "Cities")
        }
    }
}

@Composable
fun StatItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.White.copy(alpha = 0.8f)
        )
    }
}

@Composable
fun TripTabs() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        TabText("Planned", selected = true)
        Spacer(modifier = Modifier.width(24.dp))
        TabText("Visited", selected = false)
        Spacer(modifier = Modifier.width(24.dp))
        TabText("Wishlist", selected = false)
    }
}

@Composable
fun TabText(text: String, selected: Boolean) {
    Column {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = if (selected) Color(0xFF2E5B55) else Color.Gray
        )
        if (selected) {
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .width(24.dp)
                    .background(Color(0xFF2E5B55))
            )
        }
    }
}

@Composable
fun TripItem(
    title: String,
    subtitle: String,
    days: String,
    flagColor: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Flag Placeholder
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(flagColor, RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1F2D2B)
                )
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = days,
                    fontSize = 12.sp,
                    color = Color(0xFFE76F51),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}