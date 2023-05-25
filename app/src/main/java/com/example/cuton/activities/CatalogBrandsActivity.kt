package com.example.cuton.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cuton.databinding.ActivityCatalogBrandsBinding
import com.example.cuton.retrofit.Brand

class CatalogBrandsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatalogBrandsBinding
    private val adapter = BrandAdapter()

    private val brandIdsList = listOf(
        Brand( 2, "2E", "https://contestapi.rnbdev.com/image/cache/catalog/manufacturer/color/2e.png"),
        Brand( 3, "Acer", "https://contestapi.rnbdev.com/image/cache/catalog/manufacturer/color/acer.png"),
        Brand( 4, "AGM", "https://contestapi.rnbdev.com/image/cache/catalog/manufacturer/color/agm.png"),
        Brand( 5, "Alcatel", "https://contestapi.rnbdev.com/image/cache/catalog/manufacturer/color/alcatel.png"),
        Brand( 6, "Amazon", "https://contestapi.rnbdev.com/image/cache/catalog/manufacturer/color/amazon.png"),
        Brand( 7, "Apple", "https://contestapi.rnbdev.com/image/cache/catalog/manufacturer/color/apple.png"),
        Brand( 8, "Assistant", "https://contestapi.rnbdev.com/image/cache/catalog/manufacturer/color/assistant.png"),
        Brand( 9, "Asus", "https://contestapi.rnbdev.com/image/cache/catalog/manufacturer/color/asus.png"),
        Brand( 10, "BlackBerry", "https://contestapi.rnbdev.com/image/cache/catalog/manufacturer/color/blackberry.png")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCatalogBrandsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createRecyclerView()

//        DownloadImageFromInternet(findViewById(binding.rvCatalogBrands)).execute(
//            "https://contestapi.rnbdev.com/image/cache/catalog/manufacturer/color/apple.png"
//        )



//        binding.imageButtonApple.setOnClickListener {
//            // TODO implement chosen brand Apple
//            Toast.makeText(this, "Функція ще не реалізована", Toast.LENGTH_SHORT).show()
//        }
//
//        binding.imageButtonSamsung.setOnClickListener {
//            // TODO implement chosen brand Samsung
//            Toast.makeText(this, "Функція ще не реалізована", Toast.LENGTH_SHORT).show()
//        }
//
//        binding.imageButtonHuawei.setOnClickListener {
//            // TODO implement chosen brand Huawei
//            Toast.makeText(this, "Функція ще не реалізована", Toast.LENGTH_SHORT).show()
//        }
//
//        binding.imageButtonXiaomi.setOnClickListener {
//            // TODO implement chosen brand Xiaomi
//            Toast.makeText(this, "Функція ще не реалізована", Toast.LENGTH_SHORT).show()
//        }
//
//        binding.imageButtonHp.setOnClickListener {
//            // TODO implement chosen brand HP
//            Toast.makeText(this, "Функція ще не реалізована", Toast.LENGTH_SHORT).show()
//        }
//
//        binding.imageButtonAcer.setOnClickListener {
//            // TODO implement chosen brand Acer
//            Toast.makeText(this, "Функція ще не реалізована", Toast.LENGTH_SHORT).show()
//        }

    }

    private fun createRecyclerView() = with(binding) {
        rvCatalogBrands.layoutManager = GridLayoutManager(this@CatalogBrandsActivity, 2)
        rvCatalogBrands.adapter = adapter
        adapter.addAllBrands(brandIdsList)
    }

}