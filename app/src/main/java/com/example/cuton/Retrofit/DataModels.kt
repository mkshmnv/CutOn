package com.example.cuton.Retrofit

data class ApiAddressModel(val route: String? = null)

data class VersionModel(val answer: String? = null)

data class TokenModel(val token: String? = null)

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

data class ItemsModel(val token: String? = null)
