package com.example.cuton

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cuton.databinding.ActivityAuthorizationScreenBinding

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
        val stateVersion = Server.checkLatestVersion()
        println(" ---->>>> version ->>>>>> $stateVersion")

        // #2.2.2
        when (stateVersion) {
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
        }

        binding.buttonLogin.setOnClickListener {
            val intent = Intent(this, HomeMenuActivity::class.java)
            startActivity(intent)
        }
    }
}

