package com.example.cuton.api

import com.google.gson.Gson
import okhttp3.OkHttpClient

/**
 * All stuffs required for making HTTP-requests with OkHttp client and
 * for parsing JSON-messages.
 */
class OkHttpConfig(
    val baseUrl: String = "https://cr-test-ribu2uaqea-ey.a.run.app/routes/",        // prefix for all endpoints
    val client: OkHttpClient,   // for making HTTP requests
    val gson: Gson              // for parsing JSON-messages
)