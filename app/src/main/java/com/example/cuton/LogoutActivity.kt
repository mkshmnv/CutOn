package com.example.cuton

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
            binding.buttonYes.isSelected != binding.buttonYes.isSelected
            val intent = Intent(this, AuthorizationScreenActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.buttonNo.setOnClickListener {
                val intent = Intent(this, HomeMenuActivity::class.java)
                startActivity(intent)
                finish()
        }

    }

}