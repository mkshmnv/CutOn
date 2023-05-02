package com.example.cuton

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cuton.databinding.ActivityAuthorizationScreenBinding
import com.google.gson.Gson
import kotlinx.coroutines.*
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL



class AuthorizationScreenActivity : AppCompatActivity() {

    val tag = "points-Authorization"

    private lateinit var binding: ActivityAuthorizationScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthorizationScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // add the default theme here which we want
        // to display after the splash screen is shown
        setTheme(R.style.Theme_CutOn)
        setContentView(R.layout.activity_authorization_screen)

        //#2.1
        Device.setDevman(Build.MANUFACTURER)
        Device.setDevmod(Build.MODEL)
        Device.setDevavs(Build.VERSION.RELEASE)
        Device.setDevaid(Build.ID)

        // #2.2
        CoroutineScope(Dispatchers.IO).launch {
            var answer = 0
            // #2.2.1
            async {
                val url = URL("${Server.getApiAddress()}app/version/latest/?v=${Server.getV()}")
                Log.e(tag, "request -> ${Server.getApiAddress()}app/version/latest/?v=${Server.getV()}")
                val connection = url.openConnection() as HttpURLConnection
                val inputSystem = connection.inputStream
                val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")
                Log.e(tag, "responseCode -> ${connection.responseCode}")

                answer = Gson().fromJson(inputStreamReader, object {
                    val answer: Int = 0
                }::class.java).answer

                inputStreamReader.close()
                inputSystem.close()

                Log.e(tag, "response answer -> $answer")
            }.await()

            // #2.2.2
            withContext(Dispatchers.Main) {
                when (answer) {
                    2 -> {
                        Toast.makeText(
                            this@AuthorizationScreenActivity,
                            "Версія додатку занадто застаріла, без оновлення авторизація не можлива",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    1 -> {
                        Toast.makeText(
                            this@AuthorizationScreenActivity,
                            "Є нова версія додатку",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        Toast.makeText(
                            this@AuthorizationScreenActivity,
                            "ПОМИЛКА - answer is $answer",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}

