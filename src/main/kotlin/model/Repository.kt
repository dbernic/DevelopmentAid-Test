package org.example.model

import org.example.entiies.WeatherResult
import org.example.entiies.WeatherUIModel

interface Repository {
    fun fetchData(location: String, date: String): WeatherResult<WeatherUIModel>
}