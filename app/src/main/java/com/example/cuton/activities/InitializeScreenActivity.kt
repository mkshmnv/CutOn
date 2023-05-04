package com.example.cuton.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cuton.Server
import com.example.cuton.databinding.ActivityInitializeScreenBinding
import com.example.cuton.network.ApiAddressModel
import com.example.cuton.network.ApiService
import com.example.cuton.network.ServiceGenerator
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

@Suppress("DEPRECATION")
class InitializeScreenActivity : AppCompatActivity() {

    val tag = "points-Initialize"

    private lateinit var binding: ActivityInitializeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInitializeScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // #1.2.1
        Server.setAppName("cuton")

        // #1.2.2
        Server.setV("36")

        // #1.4
        if (!Server.checkForInternet(this)) {
            Log.e("point 1.4", "Помилка підключення ")
            Toast.makeText(this, "Помилка підключення", Toast.LENGTH_SHORT).show()
        } else {
            Log.e("point 1.4", "Успішне підключення ")
            Toast.makeText(this, "Успішне підключення", Toast.LENGTH_SHORT).show()
        }

        // #1.5
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getApiAddress(
            "cuton",
            "36")

        call.enqueue(object : Callback<ApiAddressModel> {
            override fun onResponse(call: Call<ApiAddressModel>, response: Response<ApiAddressModel>) {
                if (response.isSuccessful) {
                    val route = response.body()?.route!!
                    ServiceGenerator.changeApiBaseUrl(route)
                    Log.e("point 1.5", route)
                }
            }

            override fun onFailure(call: Call<ApiAddressModel>, t: Throwable) {
                t.printStackTrace()
                Log.e("point 1.5", t.message.toString())
            }
        })

        // #1.6
//        Handler(Looper.getMainLooper()).postDelayed({
//            val intent = Intent(this, AuthorizationScreenActivity::class.java)
//
//            startActivity(intent)
//            finish()
//        }, 500) // This the delayed time in milliseconds.
    }
}