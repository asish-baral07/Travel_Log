package com.example.travel_log.ui.explore_screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.travel_log.data.model.Country
import com.example.travel_log.ui.component.CategoryChip
import com.example.travel_log.ui.component.CountryCard
import com.example.travel_log.ui.component.PopularBanner
import com.example.travel_log.ui.navigation.BottomBar

@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel = hiltViewModel(),
    onCountryClick : (Country) -> Unit,
    navController: NavController
) {
    val state by viewModel.state.collectAsState() //Take the data (state) from the ViewModel and observe it as Compose state so the UI updates automatically
    var searchText by remember { mutableStateOf("") } // For Search Bar
    var selectedRegion by remember { mutableStateOf("All") } // for 4 buttons

    val filteredCountries = state.filter { country ->
        val matchesSearch =
            country.name.contains(searchText, ignoreCase = true) ||
                    (country.capital.contains(searchText, ignoreCase = true))
        val matchesRegion = selectedRegion == "All" ||
                country.region.equals(selectedRegion, ignoreCase = true)
        matchesSearch && matchesRegion
    }

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(18.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text("Where to next?")
                    Text(
                        text = "Explore",
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
                Row {
                    IconButton(onClick = {}) {
                        Icon(Icons.Outlined.Search, null)
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Outlined.Language, null)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            // SearchBar()
            OutlinedTextField(
                value = searchText, onValueChange = { searchText = it },
                placeholder = {
                    Text("Search countries, cities.....")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            val regions = listOf(
                "All",
                "Asia",
                "Europe",
                "Africa"
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                items(regions) { region ->
                    Box(
                    modifier = Modifier.clickable { selectedRegion = region }
                    ) {
                    CategoryChip(title = region, selected = selectedRegion == region)
                    }
                }
            }
                Spacer(modifier = Modifier.height(20.dp))

                // Popular Banner
                if (filteredCountries.isNotEmpty()) {
                    PopularBanner(filteredCountries.first())
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Trending Destinations",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )

                }
                Spacer(modifier = Modifier.height(16.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement   = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.height(1000.dp)
                ) {
                    items(filteredCountries) { country ->
                        CountryCard(
                            country  = country.name,
                            capital  = country.capital,
                            currency = country.currencies ?: "Unknown",
                            flag     = country.flags ?: "",
                            onClick  = { onCountryClick(country) }
                        )
                    }
                }
        }
    }
}
