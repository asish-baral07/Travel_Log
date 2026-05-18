package com.example.travel_log.ui.component

import android.R.attr.country
import android.icu.number.Precision.currency
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.travel_log.data.model.Country

@Composable
fun PopularBanner(country: Country) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {

        AsyncImage(
            model = country.flags,
            contentDescription = null,
            contentScale = ContentScale.Crop ,
            modifier = Modifier.matchParentSize()
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black.copy(0.7f)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {

            Text(
                text = "POPULAR",
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = country.name,
                color = Color.White,
                fontSize = 28.sp
            )

            Text(
                text = "${country.capital} • ${country.currencies}",
                color = Color.White
            )
        }
    }
}