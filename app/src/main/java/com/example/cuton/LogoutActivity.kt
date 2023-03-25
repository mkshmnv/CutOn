package com.example.cuton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cuton.databinding.ActivityLogouBinding

class LogoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogouBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLogouBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonYes.setOnClickListener {
            finish()
        }

        binding.buttonNo.setOnClickListener {
                val intent = Intent(this, HomeMenuActivity::class.java)
                startActivity(intent)
                finish()
        }

    }

}