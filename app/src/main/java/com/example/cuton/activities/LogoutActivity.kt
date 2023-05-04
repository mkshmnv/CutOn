package com.example.cuton.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cuton.databinding.ActivityLogoutBinding

class LogoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLogoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonYes.setOnClickListener {

            // TODO при натисканні на кнопку ТАК підключитися до API api_address/users/
            // - DELETE-параметри запиту: ?token=token
            // - отримати JSON: {"answer":int}
            finishAffinity()
        }

        binding.buttonNo.setOnClickListener {
                val intent = Intent(this, HomeMenuActivity::class.java)
                startActivity(intent)
                finish()
        }

    }

}