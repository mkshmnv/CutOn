package com.example.cuton.retrofit

import com.ebrandIdample.cuton.retrofit.CatalogBrands
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("routes/")
    fun getApiAddress(
        @Query("appName") appName: String,
        @Query("v") v: String
    ): Call<ApiAddressModel>

    // 2.2
    @GET("app/version/latest/")
    fun getAnswer(@Query("v") v: String): Call<VersionModel>

    // 2.3
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

    // 3.1
    @GET("users/")
    fun getUsers(@Query("token") token: String): Call<UsersModel>

    // 3.2
    @GET("home/menu/items/")
    fun getItems(@Query("token") token: String): Call<Items>

    @GET("catalog/brands/")
    fun getBrands(@Query("token") token: String): Call<Brands>

}