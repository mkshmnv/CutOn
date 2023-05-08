package com.example.cuton.network

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
    private var apiAddress = NetworkObject.getApiAddress()
    private val client = OkHttpClient.Builder().build()

    private var retrofit = Retrofit.Builder()
        .baseUrl(apiAddress)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun changeApiAddress(api: String) {
        apiAddress = api
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(apiAddress)
            .client(client)
            .build()

        Log.i("point #1.5.3", "Отримана відповідь значення route збережено в apiAddress")
    }

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}