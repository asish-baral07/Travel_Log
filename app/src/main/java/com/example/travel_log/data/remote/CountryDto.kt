package com.example.travel_log.data.remote

import android.graphics.Region
import android.icu.util.Currency
import androidx.core.flagging.Flags

data class CountryDto(
    val name: NameDto,
    val capital: List<String>,
    val region: String?,
    val flags: FlagDto,
    val currencies: Map<String, CurrencyDto>?,
    val languages: Map<String, String>?,
    val cca2: String?,
    val population: Long,
)

data class NameDto(val common: String)
data class FlagDto(val png: String?,
                   val svg: String?,
                   val alt: String?)
data class CurrencyDto(val name: String,
                       val symbol: String?)

