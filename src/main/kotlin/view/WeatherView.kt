package org.example.view

import org.example.entiies.WeatherUIModel

interface WeatherView {

    fun showHeader(date: String)

    fun showCityData(city: String, data: WeatherUIModel)

    fun showCityError(city: String, error: String)

}