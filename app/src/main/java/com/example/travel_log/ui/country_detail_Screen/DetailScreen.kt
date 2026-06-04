package com.example.travel_log.ui.country_detail_Screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.travel_log.data.model.Country
import com.example.travel_log.ui.navigation.BottomBar

@Composable
fun DetailContent(country: Country,
                  navController: NavController,
                  viewModel: DetailViewModel = hiltViewModel()
) {
    val isWishlist by viewModel.isWishlist.collectAsState()

    // check database when screen opens
    LaunchedEffect(Unit) {   // when DetailScreen opens runs only once
        viewModel.checkWishlist(country.code) // check is this country already inside wishlist database or not
    }
    val context = LocalContext.current
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
                    .background(Color(0xFF006D5B))
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
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    IconButton(
                        onClick= {
                            if ( isWishlist ){
                                viewModel.removeWishlist(country.code)
                                Toast.makeText(context,"Remove from Wishlist", Toast.LENGTH_SHORT).show()
                            }else{
                                viewModel.toggleWishlist(country)
                                Toast.makeText(context,"Added To Wishlist", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f))
                    ) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = null,
                            tint = if (isWishlist) Color.Red else Color.White
                        )
                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-40).dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(30.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ) {
                Column(

                    modifier = Modifier.padding(20.dp),

                    horizontalAlignment = Alignment.CenterHorizontally
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
                            "${country.capital} • ${country.population}",

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
                        onClick = {
                            viewModel.addPlannedTrip(country)
                            Toast.makeText(context,"Trip Added Successfully", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF006D5B)
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
            horizontalAlignment = Alignment.CenterHorizontally
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



