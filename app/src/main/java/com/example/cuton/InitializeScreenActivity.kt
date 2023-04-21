package com.example.cuton

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cuton.databinding.ActivityInitializeScreenBinding
import com.google.gson.Gson
import okhttp3.*
import okio.IOException

@Suppress("DEPRECATION")
class InitializeScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInitializeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInitializeScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // #1.2.1
        Server.newAppName("cuton")

        // #1.2.2
        Server.newVer("36")

        // #1.4
        if (!Server.checkForInternet(this)) {
            Toast.makeText(this, "Не вдалось підключитись", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Вдалось підключитись", Toast.LENGTH_SHORT).show()
        }

        // #1.5
        Server.getApiAddress()

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