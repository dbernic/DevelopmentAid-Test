package org.example.entiies

data class WeatherUIModel (
    val minTemp: Double,
    val maxTemp: Double,
    val humidity: Int,
    val windSpeed: Double,
    val windDirection: String?,
)