package org.example.model

import org.example.Constants
import org.example.entiies.WeatherResult
import org.example.model.dataSource.WeatherApi
import org.example.entiies.WeatherUIModel

class RepositoryImpl(
    private val datasource: WeatherApi,
): Repository {

    override fun fetchData(location: String, date: String): WeatherResult<WeatherUIModel> {

        return try {
            val response = datasource.getForecast(
                key = Constants.API_KEY,
                location = location,
                date = date
            ).execute()

            if (response.isSuccessful) {
                response.body()?.let {
                    WeatherResult.Success(it.toUiModel())
                } ?: WeatherResult.Error("Unknown error")
            } else {
                val error = response.errorBody()?.string()?:"Unknown error"
                WeatherResult.Error(error)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            val error = e.message?:"Unknown error"
            WeatherResult.Error(error)
        }

    }

}