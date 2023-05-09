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
    fun getToken(
        @Field("login") login: String?,
        @Field("password") password: String?,
        @Field("devman") devman: String?,
        @Field("devmod") devmod: String?,
        @Field("devavs") devavs: String?,
        @Field("devaid") devaid: String?
    ) : Call<TokenModel>

}