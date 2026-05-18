package com.example.travel_log.ui.explore_screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travel_log.data.model.Country
import com.example.travel_log.data.remote.CountryServiceApi
import com.example.travel_log.data.remote.RetrofitInstance.api
import com.example.travel_log.data.repository.CountryRepository
//import com.example.travelapp.data.remote.api.CountryApiService
//import com.example.travelapp.data.remote.repository.CountryRepositoryImpl
//import com.example.travelapp.domain.model.Country
//import com.example.travelapp.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

class ExploreViewModel : ViewModel() {
    private val repository = CountryRepository() // view model gets repository access
    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val state: StateFlow<List<Country>> = _countries

    init {
        getCountries()
    }
    private fun getCountries() {

        viewModelScope.launch {

            _countries.value = repository.getAll()
        }
    }
}