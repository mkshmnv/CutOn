package com.example.cuton.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import okhttp3.*

object NetworkObject {

    private lateinit var appName: String
    private lateinit var v: String
    private var apiAddress = "https://cr-test-ribu2uaqea-ey.a.run.app/"

    fun setAppName(appName: String) {
        NetworkObject.appName = appName
        Log.i("point #1.2.1", "Значення appName встановлено - $appName")
    }

    fun getAppName() = appName

    fun setV(v: String) {
        NetworkObject.v = v
        Log.i("point #1.2.2", "Значення v встановлено - $v")
    }

    fun getV() = v

    fun setApiAddress(api: String) {
        apiAddress = api
    }

    fun getApiAddress() = apiAddress
}