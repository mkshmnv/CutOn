package com.example.cuton.network

import android.os.Build
import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
    private val client = OkHttpClient.Builder().build()
    private var apiAddress = "https://cr-test-ribu2uaqea-ey.a.run.app/"
    private lateinit var token: String

    private var retrofit = Retrofit.Builder()
        .baseUrl(apiAddress)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> buildService(service: Class<T>) = retrofit.create(service)

    fun changeApiAddress(newApiAddress: String) {

        apiAddress = newApiAddress

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(apiAddress)
            .client(client)
            .build()

        Log.i("point #1.5.3", "Отримана відповідь, значення route збережено в apiAddress")
    }

    fun getApiAddress() = apiAddress

    fun setToken(newToken: String) {
        token = newToken
    }
}