package com.example.cuton

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.google.gson.Gson
import okhttp3.*
import okio.IOException

object Server {

    private lateinit var appName : String
    private lateinit var v : String

    private lateinit var apiAddress: String

    fun newAppName(appName: String) {
        this.appName = appName
    }

    fun newVer(v: String) {
        this.v = v
    }

    fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    fun getApiAddress() {

        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://cr-test-ribu2uaqea-ey.a.run.app/routes/?appName=$appName&v=$v")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {

                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    apiAddress = Gson().fromJson(response.body!!.string(), object {
                        val route: String = ""
                    }::class.java
                    ).route
                }
            }
        })
    }

    fun checkLatestVersion(): Int {
        var answer = 0

        val client = OkHttpClient()

        val request = Request.Builder()
            .url("${apiAddress}app/version/latest/?v=$v")
            .build()

        // TODO fix answer = 0

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {

                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    answer = Gson().fromJson(response.body!!.string(), object {
                        val answer: Int = 0
                    }::class.java
                    ).answer
                }
            }
        })

        return answer
    }

}