package com.example.cuton.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cuton.R
import com.example.cuton.retrofit.Brand
import com.example.cuton.databinding.BrandItemBinding
import com.squareup.picasso.Picasso

class BrandAdapter : RecyclerView.Adapter<BrandAdapter.BrandHolder>() {

    private val brandIdsList = arrayListOf<Brand>()

    class BrandHolder(brand: View) : RecyclerView.ViewHolder(brand) {
        private val binding = BrandItemBinding.bind(brand)

        fun bind(brand: Brand) = with(binding) {
            Picasso.get().load(brand.brandImage).into(ivLogoBrand)
            tvNameBrand.text = brand.brandName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.brand_item, parent, false)
        return BrandHolder(view)
    }

    override fun getItemCount(): Int {
        return brandIdsList.size // кількість раз повинен спрацювати холдер - потрібно брати з завантаженого JSON catalog/brands
    }

    override fun onBindViewHolder(holder: BrandHolder, position: Int) {
        holder.bind(brandIdsList[position])
    }

    fun addAllBrands(list: List<Brand>) {
        brandIdsList.addAll(list)
        notifyDataSetChanged()
    }

}