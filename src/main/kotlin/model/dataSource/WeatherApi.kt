package org.example.model.dataSource

import org.example.entiies.WeatherApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast.json")
    fun getForecast(
        @Query("key") key: String,
        @Query("q") location: String,
        @Query("dt") date: String,
        @Query("days") days: Int = 1,
     ): Call<WeatherApiResponse>
}