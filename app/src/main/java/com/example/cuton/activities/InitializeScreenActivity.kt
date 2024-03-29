package com.example.cuton.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cuton.retrofit.Network
import com.example.cuton.databinding.ActivityInitializeScreenBinding
import com.example.cuton.retrofit.ApiAddressModel
import com.example.cuton.retrofit.ApiService
import com.example.cuton.retrofit.ServiceGenerator
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

@Suppress("DEPRECATION")
class InitializeScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInitializeScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInitializeScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // #1.2.1
        Network.setAppName("cuton")

        // #1.2.2
        Network.setV("36")

        // #1.4
        if (!checkForInternet()) {
            Toast.makeText(this, "Інтернет-з'єднання відсутнє", Toast.LENGTH_SHORT).show()
            Log.i("point #1.4.1", "Інтернет-з'єднання відсутнє")
        } else {
            Log.i("point #1.4.1", "Інтернет-з'єднання присутнє")
        }

        // #1.5
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        Log.i("point #1.5", "Підключаємось до API")
        val call = serviceGenerator.getApiAddress(
            Network.getAppName(),
            Network.getV()
        )

        call.enqueue(object : Callback<ApiAddressModel> {
            override fun onResponse(
                call: Call<ApiAddressModel>,
                response: Response<ApiAddressModel>
            ) {
                if (!response.isSuccessful) throw IOException("Unexpected code $response")
                val route = response.body()?.route!!
                ServiceGenerator.setBaseUrl(route)
            }

            override fun onFailure(call: Call<ApiAddressModel>, t: Throwable) {
                t.printStackTrace()
            }
        })

        // #1.6
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, AuthorizationScreenActivity::class.java)
            Log.i("point #1.6", "Наступний екран AuthorizationScreen відкритий")
            startActivity(intent)
            finish()
        }, 2000) // This the delayed time in milliseconds.
    }

    private fun checkForInternet(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}