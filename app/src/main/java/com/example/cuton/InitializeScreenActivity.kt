package com.example.cuton

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cuton.databinding.ActivityInitializeScreenBinding
import com.google.gson.Gson
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.*
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

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
        Server.setVer("36")

        // #1.4
        if (!Server.checkForInternet(this)) {
            println("<<<<<<<<<<< Помилка підключення >>>>>>>>>>>>>>>")
            Toast.makeText(this, "Помилка підключення", Toast.LENGTH_SHORT).show()
        } else {
            println("<<<<<<<<<<< Успішне підключення >>>>>>>>>>>>>>>")
            Toast.makeText(this, "Успішне підключення", Toast.LENGTH_SHORT).show()
        }

        // #1.5
        Server.testRoute()


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