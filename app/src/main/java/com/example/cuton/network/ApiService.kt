package com.example.cuton.network

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
        @Body login: String,
        @Body password: String,
        @Body devman: String,
        @Body devmod: String,
        @Body devavs: String,
        @Body devaid: String
    ) : Call<TokenModel>

}