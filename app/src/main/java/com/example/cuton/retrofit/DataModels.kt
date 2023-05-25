package com.example.cuton.retrofit

data class ApiAddressModel(val route: String? = null)

data class VersionModel(val answer: String? = null)

data class TokenModel(val detail: String? = null, val token: String? = null)

data class UsersModel(
    val userId: Int? = null,
    val firstname: String? = null,
    val lastname: String? = null,
    val pmFirstname: String? = null,
    val pmLastname: String? = null,
    val pmTelephone: String? = null,
    val tsFirstname: String? = null,
    val tsLastname: String? = null,
    val tsTelephone: String? = null,
    val balance: Int? = null,
    val bonusToday: Int? = null,
    val bonusTotal: Int? = null,
    val bonusTitle: String? = null
)

data class Items(
    val items: Item?
)
data class Item(
    val itemId: Int,
    val itemName: String,
    val itemImage: String
)





data class Brands(
    val brands: BrandIds?
)

data class BrandIds(
    val ids: List<Brand>
)

data class Brand(
    val brandId: Int?,
    val brandName: String?,
    val brandImageURL: String?
)

