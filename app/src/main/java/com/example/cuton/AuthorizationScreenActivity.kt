package com.example.cuton

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cuton.databinding.ActivityAuthorizationScreenBinding

class AuthorizationScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthorizationScreenBinding

    private val device = Device()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // add the default theme here which we want
        // to display after the splash screen is shown
        setTheme(R.style.Theme_CutOn)
        setContentView(R.layout.activity_authorization_screen)

        device.newDevman(Build.MANUFACTURER)
        device.newDevmod(Build.MODEL)
        device.newDevavs(Build.VERSION.RELEASE)
        device.newDevaid(Build.ID)

        binding = ActivityAuthorizationScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonLogin.setOnClickListener {
            val intent = Intent(this, HomeMenuActivity::class.java)
//          TODO implement ---> intent.putExtras(device)
            startActivity(intent)
        }
    }
}

