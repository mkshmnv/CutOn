package com.example.cuton

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cuton.databinding.ActivityInitializeScreenBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

const val TAGi = "points-InitializeScreen"


@Suppress("DEPRECATION")
class InitializeScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInitializeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInitializeScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // #1.2.1
        Server.setAppName("cuton")

        // #1.2.2
        Server.setV("36")

        // #1.4
        if (!Server.checkForInternet(this)) {
            println("<<<<<<<<<<< Помилка підключення >>>>>>>>>>>>>>>")
            Toast.makeText(this, "Помилка підключення", Toast.LENGTH_SHORT).show()
        } else {
            println("<<<<<<<<<<< Успішне підключення >>>>>>>>>>>>>>>")
            Toast.makeText(this, "Успішне підключення", Toast.LENGTH_SHORT).show()
        }

        // #1.5
        CoroutineScope(Dispatchers.IO).launch {
            var response = ""
            async {
                val url = URL(
                    "https://cr-test-ribu2uaqea-ey.a.run.app/routes/?appName=${Server.getAppName()}&v=${Server.getV()}"
                )
                val connection = url.openConnection() as HttpURLConnection
                val inputSystem = connection.inputStream
                val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")

                response = Gson().fromJson(inputStreamReader, object {
                    val route: String = ""
                }::class.java).route

                inputStreamReader.close()
                inputSystem.close()
                Log.e(TAGi, "apiAddress > $response")
            }.await()

            Server.setApiAddress(response)
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // #1.6
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, AuthorizationScreenActivity::class.java)

            startActivity(intent)
            finish()
        }, 500) // This the delayed time in milliseconds.
    }
}