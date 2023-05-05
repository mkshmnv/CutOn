package com.example.cuton.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {

    @GET("routes/")
    fun getApiAddress(
        @Query("appName") appName: String,
        @Query("v") v: String
    ) : Call<ApiAddressModel>

    @GET("app/version/latest/")
    fun getAnswer(
        @Query("v") v: String
    ) : Call<VersionModel>

    @POST("users/login/")
    fun getToken(
// TODO crete POST request
        @Body
        login: String

//        @Query(value = "login", encoded = true) login: String?,
//        @Query(value = "password", encoded = true) password: String?,
//        @Query(value = "devman", encoded = true) devman: String?,
//        @Query(value = "devmod", encoded = true) devmod: String?,
//        @Query(value = "devavs", encoded = true) devavs: String?,
//        @Query(value = "devaid", encoded = true) devaid: String?
    ) : Call<TokenModel>

}