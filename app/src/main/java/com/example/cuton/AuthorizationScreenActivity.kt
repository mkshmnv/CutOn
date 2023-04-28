package com.example.cuton

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cuton.databinding.ActivityAuthorizationScreenBinding
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

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

        val answer = 0 //Server.testAnswer()

        // #2.2.2
        when (answer) {
            2 -> {
                Toast.makeText(
                    this,
                    "Версія додатку занадто застаріла, без оновлення авторизація не можлива",
                    Toast.LENGTH_SHORT
                ).show()
            }
            1 -> {
                Toast.makeText(
                    this,
                    "Є нова версія додатку",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                Toast.makeText(
                    this,
                    "ПОМИЛКА - answer is ${Server.answer1}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.buttonLogin.setOnClickListener {
            val intent = Intent(this, HomeMenuActivity::class.java)
            startActivity(intent)
        }
    }
}

