package com.example.cuton.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cuton.Device
import com.example.cuton.R
import com.example.cuton.databinding.ActivityAuthorizationScreenBinding
import com.example.cuton.network.ApiService
import com.example.cuton.network.ServiceGenerator
import com.example.cuton.network.VersionModel
import okio.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthorizationScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthorizationScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthorizationScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // add the default theme here which we want
        // to display after the splash screen is shown
        setTheme(R.style.Theme_CutOn)

        //#2.1
        Device.setDevman(Build.MANUFACTURER)
        Device.setDevmod(Build.MODEL)
        Device.setDevavs(Build.VERSION.RELEASE)
        Device.setDevaid(Build.ID)

        // #2.2
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getAnswer("36")

        call.enqueue(object : Callback<VersionModel> {
            override fun onResponse(call: Call<VersionModel>, response: Response<VersionModel>) {
                if (!response.isSuccessful) throw IOException("Unexpected code $response")
                Log.e("point #2.2", response.body().toString())
            }

            override fun onFailure(call: Call<VersionModel>, t: Throwable) {
                t.printStackTrace()
                Log.e("point #2.2", t.message.toString())
            }
        })

        // #2.3
        binding.buttonLogin.setOnClickListener {
            Log.e("point #2.3", "TODO send POST request")
        }


    }
}

