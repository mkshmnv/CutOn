package com.example.cuton.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cuton.databinding.ActivityHomeMenuBinding
import com.example.cuton.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

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

        // #3.1
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        Log.i("point #3.1", "Проводимо підключення до API для отримання Users")

        // #3.1.1
        val call = serviceGenerator.getUsers(NetworkObject.getToken())

        // Logging #3.1.1
        val getToken = "?token=${NetworkObject.getToken()}}"
        Log.i(
            "point #3.1.1",
            "GET-параметри запиту: $getToken. Повний запит ${ServiceGenerator.getApiAddress()}$getToken"
        )

        call.enqueue(object : Callback<UsersModel> {
            override fun onResponse(
                call: Call<UsersModel>,
                response: Response<UsersModel>
            ) {
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                val users = response.body()
                Log.i("point #3.1.2", "Отримана JSON відповідь: ${response.body()}")

            }

            override fun onFailure(call: Call<UsersModel>, t: Throwable) {
                t.printStackTrace()
                Log.e("point #1.5", "Неотримана JSON відповідь: ${t.message.toString()}")
            }
        })
    }
}