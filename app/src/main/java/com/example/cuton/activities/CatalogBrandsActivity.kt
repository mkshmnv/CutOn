package com.example.cuton.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cuton.databinding.ActivityCatalogBrandsBinding
import com.example.cuton.retrofit.ApiService
import com.example.cuton.retrofit.Brand
import com.example.cuton.retrofit.BrandIds
import com.example.cuton.retrofit.Brands
import com.example.cuton.retrofit.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class CatalogBrandsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatalogBrandsBinding
    private val adapter = BrandAdapter()

    private val brandIdsList = listOf(
        Brand(
            2,
            "2E",
            "https://contestapi.rnbdev.com/image/cache/catalog/manufacturer/color/2e.png"
        ),
        Brand(
            3,
            "Acer",
            "https://contestapi.rnbdev.com/image/cache/catalog/manufacturer/color/acer.png"
        ),
        Brand(
            4,
            "AGM",
            "https://contestapi.rnbdev.com/image/cache/catalog/manufacturer/color/agm.png"
        ),
        Brand(
            5,
            "Alcatel",
            "https://contestapi.rnbdev.com/image/cache/catalog/manufacturer/color/alcatel.png"
        ),
        Brand(
            6,
            "Amazon",
            "https://contestapi.rnbdev.com/image/cache/catalog/manufacturer/color/amazon.png"
        ),
        Brand(
            7,
            "Apple",
            "https://contestapi.rnbdev.com/image/cache/catalog/manufacturer/color/apple.png"
        ),
        Brand(
            8,
            "Assistant",
            "https://contestapi.rnbdev.com/image/cache/catalog/manufacturer/color/assistant.png"
        ),
        Brand(
            9,
            "Asus",
            "https://contestapi.rnbdev.com/image/cache/catalog/manufacturer/color/asus.png"
        ),
        Brand(
            10,
            "BlackBerry",
            "https://contestapi.rnbdev.com/image/cache/catalog/manufacturer/color/blackberry.png"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCatalogBrandsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)

        Log.i("point #3.1", "Підключаємось до API для отримання Brands")
        val call = serviceGenerator.getBrands("MzgwOTM4ODQ1Mzk06T1Wbh")

        call.enqueue(object : Callback<BrandIds> {
            override fun onResponse(call: Call<BrandIds>, response: Response<BrandIds>) {
                if (!response.isSuccessful) throw IOException("Unexpected code $response") // TODO fis first runtime error

                val brands = response.body()!!

                Log.i("point #4.1.2", "brands - values: ${brands}")
            }

            override fun onFailure(call: Call<BrandIds>, t: Throwable) {
                t.printStackTrace()
            }

        })

        // fill RecyclerView
        binding.rvCatalogBrands.layoutManager = GridLayoutManager(this, 2)
        binding.rvCatalogBrands.adapter = adapter

        adapter.addAllBrands(brandIdsList)

    }

}