package com.example.travel_log.ui.component

import android.R.attr.country
import android.icu.number.Precision.currency
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun CountryCard(
    country: String,
    capital: String,
    currency: String,
    flag: String,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .width(170.dp)
            .padding(6.dp)
            .clickable{ onClick() },
        shape = RoundedCornerShape(24.dp)
    ) {

        Column {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color(0xFFEDEDED))
            ) {

                AsyncImage(
                    model = flag,
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = country,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "$capital • $currency",
                    color = Color.Gray
                )
            }
        }
    }
}
