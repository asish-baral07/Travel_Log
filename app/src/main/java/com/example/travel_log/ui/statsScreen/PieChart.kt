package com.example.travel_log.ui.statsScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PieChart(
    visited: Float,
    wishlist: Float,
    planned: Float
) {

    val total = visited + wishlist + planned

    // Convert Values to angles
    val visitedAngle = (visited / total) * 360f
    val wishlistAngle = (wishlist / total) * 360f
    val plannedAngle = (planned / total) * 360f
    Column {
        Canvas( // creates a drawing surface
            modifier = Modifier
                .size(220.dp)
        ) {
            var startAngle = -90f    //This makes the chart start from the top (12 o'clock)
            drawArc(
                color = Color(0xFF00796B),
                startAngle = startAngle,
                sweepAngle = visitedAngle,  // how much it covers
                useCenter = false,  // Doesn't draw from center → makes it ring style
                style = Stroke(
                    width = 60f,
                    cap = StrokeCap.Round
                ),
                size = Size(size.width, size.height),
                topLeft = Offset.Zero
            )
            startAngle += visitedAngle     // Next arc starts where previous one ended
            drawArc(
                color = Color(0xFF8E24AA),
                startAngle = startAngle,
                sweepAngle = wishlistAngle,
                useCenter = false,
                style = Stroke(
                    width = 60f,
                    cap = StrokeCap.Round
                ),
                size = Size(size.width, size.height),
                topLeft = Offset.Zero
            )
            startAngle += wishlistAngle
            drawArc(
                color = Color(0xFFFF9800),
                startAngle = startAngle,
                sweepAngle = plannedAngle,
                useCenter = false,
                style = Stroke(
                    width = 60f,
                    cap = StrokeCap.Round
                ),
                size = Size(size.width, size.height),
                topLeft = Offset.Zero
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            LegendItem(
                color = Color(0xFF00796B),
                text = "Visited"
            )

            LegendItem(
                color = Color(0xFF8E24AA),
                text = "Wishlist"
            )

            LegendItem(
                color = Color(0xFFFF9800),
                text = "Planned"
            )
        }
    }
}

@Composable
fun LegendItem(
    color: Color,
    text: String
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Canvas(
            modifier = Modifier.size(16.dp)
        ) {
            drawCircle(color = color)  // Draws a small dot representing the category color
        }

        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}