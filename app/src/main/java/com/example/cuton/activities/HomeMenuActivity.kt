package com.example.cuton.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cuton.databinding.ActivityHomeMenuBinding
import com.example.cuton.Retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class HomeMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeMenuBinding

    val token = Network.getToken()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)

        // #3.1
        usersApiRequest(serviceGenerator)
        itemsApiRequest(serviceGenerator)

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


    private fun usersApiRequest(serviceGenerator: ApiService) {
        // #3.1
        Log.i("point #3.1", "Підключаємось до API для отримання Users")

        // #3.1.1
        val call = serviceGenerator.getUsers(token)

        call.enqueue(object : Callback<UsersModel> {
            override fun onResponse(
                call: Call<UsersModel>,
                response: Response<UsersModel>
            ) {
                if (!response.isSuccessful) throw IOException("Unexpected code $response")
                // TODO test case
            }

            override fun onFailure(call: Call<UsersModel>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun itemsApiRequest(serviceGenerator: ApiService) {
        // #3.1
        Log.i("point #3.2", "Підключаємось до API для отримання Items")

        // #3.1.1
        val call = serviceGenerator.getItems(token)

        call.enqueue(object : Callback<ItemsModel> {
            override fun onResponse(
                call: Call<ItemsModel>,
                response: Response<ItemsModel>
            ) {
                if (!response.isSuccessful) throw IOException("Unexpected code $response")
                // TODO test case
            }

            override fun onFailure(call: Call<ItemsModel>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}