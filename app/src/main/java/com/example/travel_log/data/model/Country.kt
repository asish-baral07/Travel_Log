package com.example.travel_log.data.model

data class Country(
    val code: String,
    val name: String,
    val capital: String,
    val region: String?,
    val flags: String?,
    val population: Long,
    val currencies: String?,
    val languages: String
)
