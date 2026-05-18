package com.example.travel_log.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface CountryServiceApi {
    @GET("all")
    suspend fun getCountries(
        @Query("fields")
        fields: String = "name,capital,region,flags,population,languages,currencies,cca2"

    ): List<CountryDto>
}