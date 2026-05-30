package com.example.travel_log.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.travel_log.data.local.TripEntity

@Composable
fun TripCard(
    trip: TripEntity,
    buttonText: String,
    onButtonClick : () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding( vertical = 7.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = trip.flag,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column (
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = trip.countryName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = trip.capital
                )
                Text(
                    text = trip.region ?: ""
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = when (trip.type.name) {
                            "PLANNED" -> Color(0xFFD6E4FF)
                            "WISHLIST" -> Color(0xFFFFD6E7)
                            else -> Color(0xFFD7F5E8)
                        }
                    ) {

                        Text(
                            text = trip.type.name.uppercase(),
                            modifier = Modifier.padding(
                                horizontal = 14.dp,
                                vertical = 6.dp
                            ),
                            color = when (trip.type.name) {
                                "PLANNED" -> Color(0xFF3F51B5)
                                "WISHLIST" -> Color(0xFFE91E63)
                                else -> Color(0xFF00796B)
                            },
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    }

                    if(buttonText.isNotEmpty()){
                        Button(
                            onClick = onButtonClick,
                            modifier = Modifier
                                .height(42.dp)
                                .width(150.dp),
                            shape = RoundedCornerShape(18.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF006D5B)
                            ),
                            contentPadding = PaddingValues(
                                horizontal = 10.dp,
                                vertical = 4.dp
                            )
                        ) {
                            Text(
                                text = buttonText,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}