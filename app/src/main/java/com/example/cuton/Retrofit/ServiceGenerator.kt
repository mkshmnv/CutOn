package com.example.cuton.Retrofit

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    private val interceptor = HttpLoggingInterceptor()

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private var apiAddress = "https://cr-test-ribu2uaqea-ey.a.run.app/"
    private lateinit var token: String

    init {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    }



//    val client = OkHttpClient.Builder().addInterceptor(interceptor)

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

        Log.i("point #1.5.3", "Значення route збережено в apiAddress")
    }

    fun getApiAddress() = apiAddress

    fun setToken(newToken: String) {
        token = newToken
    }
}