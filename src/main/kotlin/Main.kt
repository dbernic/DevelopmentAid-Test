package org.example;

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.example.controller.WeatherController
import org.example.model.RepositoryImpl
import org.example.model.dataSource.WeatherApi
import org.example.view.WeatherViewImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun main(args: Array<String>) {

    val dataSource: WeatherApi = getRetrofit().create(WeatherApi::class.java)
    val repository = RepositoryImpl(dataSource)
    val view = WeatherViewImpl()

    val controller = WeatherController(
        view = view,
        repository = repository,
    )

    val cities = listOf(
        "Kyiv",
        "Madrid",
        "Chisinau",
        "Amsterdam",
    )

    controller.run(cities)

}

private fun getRetrofit(): Retrofit {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    val okHttpClient = OkHttpClient.Builder().apply {
        readTimeout(60, TimeUnit.SECONDS)
        connectTimeout(60, TimeUnit.SECONDS)
        addInterceptor(loggingInterceptor)
    }.build()

    val builder = Retrofit.Builder().apply {
        client(okHttpClient)
        addConverterFactory(GsonConverterFactory.create(Gson()))
    }

    return builder.baseUrl(Constants.SERVER_URL).build()
}

