package com.example.cuton.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
    private var apiBaseUrl = "https://cr-test-ribu2uaqea-ey.a.run.app/"
    private val client = OkHttpClient.Builder().build()

    private var retrofit = Retrofit.Builder()
        .baseUrl(apiBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun changeApiBaseUrl(newApiBaseUrl: String) {
        apiBaseUrl = newApiBaseUrl
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(apiBaseUrl)
            .client(client)
            .build()
    }

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}