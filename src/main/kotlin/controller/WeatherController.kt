package org.example.controller

import org.example.entiies.WeatherResult
import org.example.model.Repository
import org.example.view.WeatherView
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class WeatherController (
    private val view: WeatherView,
    private val repository: Repository,
) {

    fun run(cities: List<String>) {

        val date = getTomorrowDate()
        val apiDate = date.getApiDate()

        view.showHeader(date.getViewDate())
        cities.forEach { city ->
            when (val result = repository.fetchData(city, apiDate)) {
                is WeatherResult.Success -> {
                    view.showCityData(city, result.data)
                }
                is WeatherResult.Error -> {
                    view.showCityError(city, result.message)
                }
            }
        }
    }


    fun getTomorrowDate(): LocalDate {
        val today = LocalDate.now()
        return today.plusDays(1)
    }
}

fun LocalDate.getApiDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return this.format(formatter)
}

fun LocalDate.getViewDate(): String {
    val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH)
    return this.format(formatter)
}