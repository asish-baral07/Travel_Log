package com.example.travel_log.ui.details_screen

import android.R.attr.country
import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.travel_log.data.model.Country
import com.example.travel_log.ui.explore_screens.ExploreViewModel
import com.example.travel_log.ui.navigation.BottomBar
@Composable
fun DetailContent(country: Country, navController: NavController) {

    Scaffold(
        bottomBar ={ BottomBar(navController) }
    ) { paddingValues ->
        Column(

            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(paddingValues),
        ) {
            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .background(Color(0xFF4FA4A0))

            ) {

                AsyncImage(
                    model = country.flags,
                    contentDescription = null,
                    modifier = Modifier
                        .size(250.dp)
                        .align(Alignment.Center),

                    contentScale = ContentScale.Fit
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f))
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    IconButton(
                        onClick= {},
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f))
                    ) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }

            Card(

                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-30).dp),
                    //.fillMaxHeight(),

                shape = RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp
                )
            ) {

                Column(

                    modifier = Modifier.padding(20.dp),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(

                        text = "${country.region} • Country",

                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(

                        text = country.name,

                        fontSize = 34.sp,

                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(

                        text =
                            "${country.capital ?: "Unknown"} • ${country.population}",

                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(

                        horizontalArrangement =
                            Arrangement.spacedBy(12.dp)
                    ) {

                        InfoCard(

                            title = country.currencies ?: "",

                            subtitle = "Currency"
                        )

                        InfoCard(

                            title = country.languages,

                            subtitle = "Language"
                        )

                        InfoCard(

                            title = "GMT",

                            subtitle = "Timezone"
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(

                        text =
                            "${country.name} is a beautiful country in ${country.region}.",

                        lineHeight = 26.sp
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Button(

                        onClick = { },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4FA4A0)
                        )
                    ) {

                        Text(
                            text = "+  Plan a Trip",
                            fontSize = 20.sp,

                        )
                    }

                }
            }
        }
    }

}
@Composable
fun InfoCard(
    title: String,
    subtitle: String
) {

    Card(

        modifier = Modifier.size(
            width = 100.dp,
            height = 90.dp
        )
    ) {

        Column(

            modifier = Modifier.fillMaxSize(),

            verticalArrangement =
                Arrangement.Center,

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Text(

                text = title,

                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(text = subtitle)
        }
    }
}



