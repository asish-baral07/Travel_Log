package com.example.travel_log.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CategoryChip(title: String , selected: Boolean) {

    val bg =
        if (selected) Color(0xFF0B8B74)
        else Color(0xFFF6EFED)

    val text =
        if (selected) Color.White
        else Color.Black

    Text(
        text = title,
        color = text,
        modifier = Modifier
            .background(
                bg,
                RoundedCornerShape(16.dp)
            )

            .padding(
                horizontal = 20.dp,
                vertical = 7.dp
            )
    )
}