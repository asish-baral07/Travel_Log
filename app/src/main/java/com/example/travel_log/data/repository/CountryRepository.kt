package com.example.travel_log.data.repository

import com.example.travel_log.data.model.Country
import com.example.travel_log.data.remote.CountryServiceApi
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val api : CountryServiceApi
) {
    suspend fun getAll(): List<Country> {

        return api.getCountries().map {
            Country(
                code = it.cca2,
                name = it.name.common,
                capital = it.capital?.firstOrNull() ?: "Unknown",
                region = it.region,
                flags = it.flags.png,
                population = it.population,
                currencies =  it.currencies?.values?.firstOrNull()?.symbol ?: "Unknown",
                languages = it.languages?.values?.firstOrNull() ?: "Unknown"
            )
        }
    }
}

