package com.example.cuton.network

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("routes/")
    fun getApiAddress(
        @Query("appName") appName: String,
        @Query("v") v: String
    ): Call<ApiAddressModel>

    @GET("app/version/latest/")
    fun getAnswer(
        @Query("v") v: String
    ): Call<VersionModel>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("users/login/")
    fun token(
        @Field("login") login: String = "380501234567",
        @Field("password") password: String = "123456",
        @Field("devman") devman: String = "Xiaomi",
        @Field("devmod") devmod: String = "RedmiNote",
        @Field("devavs") devavs: String = "Q",
        @Field("devaid") devaid: String = "31"
    ) : Call<TokenModel>

}