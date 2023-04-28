package com.example.cuton

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.google.gson.Gson
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object Server {

    private lateinit var appName: String
    private lateinit var v: String

    private lateinit var apiAddress: String
    var answer1: Int = 0

    fun setAppName(appName: String) {
        this.appName = appName
    }

    fun getAppName() = appName

    fun setVer(v: String) {
        this.v = v
    }

    fun getVer() = v

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

    fun setApiAddress(api: String) {
        apiAddress = api
    }

    fun getApiAddress() = apiAddress

    fun testRoute() {
        runBlocking { // this: CoroutineScope
            launch { // launch a new coroutine and continue
                async {
                    val url = URL(
                        "https://cr-test-ribu2uaqea-ey.a.run.app/routes/?appName=$appName&v=$v"
                    )
                    val connection = url.openConnection() as HttpURLConnection
                    val inputSystem = connection.inputStream
                    val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")

                    apiAddress = Gson().fromJson(inputStreamReader, object {
                        val route: String = ""
                    }::class.java).route

                    inputStreamReader.close()
                    inputSystem.close()

                    println("--------------!!!apiAddress!!!--------------->>>>>>>>>>>>>>>>> ${Server.getApiAddress()}")
                }.await()
            }
        }
    }

    fun testAnswer() : Int {
        var answ = 0
        runBlocking { // this: CoroutineScope
            launch { // launch a new coroutine and continue
                async {
                    val url = URL("${apiAddress}app/version/latest/?v=$v")
                    val connection = url.openConnection() as HttpURLConnection
                    val inputSystem = connection.inputStream
                    val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")

                    answ = Gson().fromJson(inputStreamReader, object {
                        val answer: Int = 0
                    }::class.java).answer

                    inputStreamReader.close()
                    inputSystem.close()

                    println(" ---->>>> version ->>>>>> $answ")
                }.await()
            }
        }
        return answ
    }

//
//    private fun getTest() {
//
//        val client = OkHttpClient.Builder().build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://cr-test-ribu2uaqea-ey.a.run.app/routes/?appName=$appName&v=$v")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//
//        fun <T> buildService(service: Class<T>): T {
//            return retrofit.create(service)
//        }
//
//        val request = Request.Builder()
//            .url("https://cr-test-ribu2uaqea-ey.a.run.app/routes/?appName=$appName&v=$v")
//            .build()
//
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                e.printStackTrace()
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//
//                response.use {
//                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
//
//                    answer1 = Gson().fromJson(
//                        response.body!!.string(), object {
//                            val answ: Int = 0
//                        }::class.java
//                    ).answ
//                }
//
//                answer1
//            }
//        })
//    }
//
//    fun checkLatestVersion() {
//
//
//        val client = OkHttpClient()
//
//        val request = Request.Builder()
//            .url("${apiAddress}app/version/latest/?v=$v")
//            .build()
//
//        // TODO fix answer = 0
//
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                e.printStackTrace()
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//
//                response.use {
//                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
//
//                    answer1 = Gson().fromJson(
//                        response.body!!.string(), object {
//                            val answer: Int = 0
//                        }::class.java
//                    ).answer
//                }
//            }
//        })
//        answer1
//    }

}