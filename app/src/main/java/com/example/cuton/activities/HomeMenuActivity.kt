package com.example.cuton.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cuton.databinding.ActivityHomeMenuBinding

class HomeMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonClose.setOnClickListener {
            val intent = Intent(this, LogoutActivity::class.java)
            startActivity(intent)
        }

        binding.buttonCatalog.setOnClickListener {
            val intent = Intent(this, CatalogBrandsActivity::class.java)
            startActivity(intent)
        }

        binding.buttonProfile.setOnClickListener {
            Toast.makeText(this, "Функція Profile ще не реалізована", Toast.LENGTH_SHORT).show()
        }
    }
}