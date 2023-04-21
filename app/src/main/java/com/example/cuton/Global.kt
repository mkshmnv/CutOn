package com.example.cuton

import android.app.Application

class Global : Application() {
    companion object {
        @JvmField
        val appName = "CutOn"
        val v = 36
        val baseApi = "https://cr-test-ribu2uaqea-ey.a.run.app/routes/"
    }
}