package com.example.cuton.network

import android.util.Log

object NetworkObject {

    private lateinit var appName: String
    private lateinit var v: String

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

}