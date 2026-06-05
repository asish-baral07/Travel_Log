package com.example.travel_log.ui.country_detail_Screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travel_log.data.local.TripEntity
import com.example.travel_log.data.model.Country
import com.example.travel_log.data.model.TripType
import com.example.travel_log.data.repository.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: TripRepository
) : ViewModel() {
    private val _isWishlist = MutableStateFlow(false)
    val isWishlist = _isWishlist.asStateFlow()
    fun addPlannedTrip(country: Country) {
        viewModelScope.launch {
            repository.insertTrip(
                TripEntity(
                    countryCode = country.code,
                    countryName = country.name,
                    capital = country.capital,
                    region = country.region,
                    flag = country.flags,
                    type = TripType.PLANNED
                )
            )
        }
    }

    fun checkWishlist(code : String){
        viewModelScope.launch {
            repository.isWishlist(code).collect { _isWishlist.value = it }
        }
    }
    // Toggle Function : Checks current status, If exist -> remove || If not exists -> insert to wishlist tab



    fun toggleWishlist(country: Country){ // acts like on/off switch for wishlist button
        viewModelScope.launch {
            if (_isWishlist.value){
                repository.removeWishlist(country.code)
            }
            else{
                repository.insertTrip(
                    TripEntity(
                        countryCode = country.code,
                        countryName = country.name,
                        capital = country.capital,
                        region = country.region,
                        flag = country.flags,
                        type = TripType.WISHLIST
                    )
                )
            }
        }
    }

    fun removeWishlist(countryCode: String){
        viewModelScope.launch{
            repository.removeWishlist(countryCode)
                _isWishlist.value = false
        }
    }
}


/*
This file is responsible for , receiving country data from UI, converting into TripEntity,
saving it into Room Database.

- launch means run the code asynchronously
*/