package com.example.cuton

import android.content.Intent
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

const val TAGa = "points-Authorization"

class AuthorizationScreenActivity : AppCompatActivity() {

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
        Device.newDevman(Build.MANUFACTURER)
        Device.newDevmod(Build.MODEL)
        Device.newDevavs(Build.VERSION.RELEASE)
        Device.newDevaid(Build.ID)

        // #2.2
        var answer = 0
        CoroutineScope(Dispatchers.IO).launch {
            // #2.2.1
            async {
                val url = URL("${Server.getApiAddress()}app/version/latest/?v=${Server.getV()}")
                Log.e(TAGa, "${Server.getApiAddress()}app/version/latest/?v=${Server.getV()}")
                val connection = url.openConnection() as HttpURLConnection
                val inputSystem = connection.inputStream
                val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")

                answer = Gson().fromJson(inputStreamReader, object {
                    val answer: Int = 0
                }::class.java).answer

                inputStreamReader.close()
                inputSystem.close()

                Log.e(TAGa, "answer - $answer")
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

                        Log.e(
                            TAGa,
                            "Версія додатку занадто застаріла, без оновлення авторизація не можлива"
                        )
                    }
                    1 -> {
                        Toast.makeText(
                            this@AuthorizationScreenActivity,
                            "Є нова версія додатку",
                            Toast.LENGTH_SHORT
                        ).show()

                        Log.e(TAGa, "Є нова версія додатку")
                    }
                    else -> {
                        Toast.makeText(
                            this@AuthorizationScreenActivity,
                            "ПОМИЛКА - answer is $answer",
                            Toast.LENGTH_SHORT
                        ).show()

                        Log.e(TAGa, "ПОМИЛКА - answer is $answer")
                    }
                }
            }
        }
    }
}

