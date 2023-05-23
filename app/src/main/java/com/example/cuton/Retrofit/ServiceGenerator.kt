package com.example.cuton.Retrofit

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    private var baseUrl = "https://cr-test-ribu2uaqea-ey.a.run.app/"

    private val interceptor = HttpLoggingInterceptor()

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    init {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    private var retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> buildService(service: Class<T>) = retrofit.create(service)

    fun setBaseUrl(newBaseUrl: String) {

        baseUrl = newBaseUrl

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(client)
            .build()

        Log.i("point #1.5.3", "Значення route збережено в apiAddress")
    }

}