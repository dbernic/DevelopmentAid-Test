package org.example.entiies

import com.google.gson.annotations.SerializedName


data class WeatherApiResponse(
    val location: Location,
    val forecast: Forecast,
) {
    fun toUiModel(): WeatherUIModel {
        val dayWeather = forecast.forecastday.first().day
        val direction = forecast.forecastday.first().hour
            .map { it.windDirection }
            .groupingBy { it }
            .eachCount()
            .maxByOrNull { it.value }?.key

        return WeatherUIModel(
            minTemp = dayWeather.minTemp,
            maxTemp = dayWeather.maxTemp,
            humidity = dayWeather.humidity,
            windSpeed = dayWeather.windSpeed,
            windDirection = direction,

        )
    }
}

data class Location(
    val name: String,
    val region: String,
    val country: String
)

data class Forecast(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val day: Day,
    val hour: List<Hourly>

)

data class Day(
    @SerializedName("maxtemp_c")
    val maxTemp: Double,
    @SerializedName("mintemp_c")
    val minTemp: Double,
    @SerializedName("maxwind_kph")
    val windSpeed: Double,
    @SerializedName("avghumidity")
    val humidity: Int,
)


data class Hourly(
    @SerializedName("wind_dir")
    val windDirection: String,
)
