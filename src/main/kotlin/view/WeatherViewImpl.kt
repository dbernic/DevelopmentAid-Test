package org.example.view

import org.example.entiies.WeatherUIModel

class WeatherViewImpl: WeatherView {

    override fun showHeader(date: String) {
        println("Weather for $date")
        println("| %-15s | %-7s | %-7s | %-7s | %-7s | %-7s |".format(
            "City", "Min t", "Max t", "Hum.%", "W kph", "W dir"
        ))
    }

    override fun showCityData(city: String, data: WeatherUIModel) {
        val formatString = "| %-15s | %7.1f | %7.1f | %7d | %7.1f | %-7s |"
        println(String.format(
            formatString,
            city,
            data.minTemp,
            data.maxTemp,
            data.humidity,
            data.windSpeed,
            data.windDirection
        ))
    }

    override fun showCityError(city: String, error: String) {
        val formatString = "| %-15s | %-35s |"
        println(String.format(
            formatString,
            city,
            error
        ))
    }

}