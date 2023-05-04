package com.example.cuton.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cuton.databinding.ActivityCatalogBrandsBinding

class CatalogBrandsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatalogBrandsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCatalogBrandsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButtonApple.setOnClickListener {
            // TODO implement chosen brand Apple
            Toast.makeText(this, "Функція ще не реалізована", Toast.LENGTH_SHORT).show()
        }

        binding.imageButtonSamsung.setOnClickListener {
            // TODO implement chosen brand Samsung
            Toast.makeText(this, "Функція ще не реалізована", Toast.LENGTH_SHORT).show()
        }

        binding.imageButtonHuawei.setOnClickListener {
            // TODO implement chosen brand Huawei
            Toast.makeText(this, "Функція ще не реалізована", Toast.LENGTH_SHORT).show()
        }

        binding.imageButtonXiaomi.setOnClickListener {
            // TODO implement chosen brand Xiaomi
            Toast.makeText(this, "Функція ще не реалізована", Toast.LENGTH_SHORT).show()
        }

        binding.imageButtonHp.setOnClickListener {
            // TODO implement chosen brand HP
            Toast.makeText(this, "Функція ще не реалізована", Toast.LENGTH_SHORT).show()
        }

        binding.imageButtonAcer.setOnClickListener {
            // TODO implement chosen brand Acer
            Toast.makeText(this, "Функція ще не реалізована", Toast.LENGTH_SHORT).show()
        }

    }

}