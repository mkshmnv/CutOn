package com.example.cuton.network

import android.util.Log

object NetworkObject {

    private lateinit var appName: String
    private lateinit var v: String
    private lateinit var token: String

    fun setAppName(appName: String) {
        this.appName = appName
        Log.i("point #1.2.1", "Значення appName встановлено - $appName")
    }

    fun getAppName() = appName

    fun setV(v: String) {
        this.v = v
        Log.i("point #1.2.2", "Значення v встановлено - $v")
    }

    fun getV() = v

    fun setToken(v: String) {
        this.token = token
        Log.i("point #2.3.3.1", "Значення token встановлено - $token")
    }

    fun getToken() = token

}