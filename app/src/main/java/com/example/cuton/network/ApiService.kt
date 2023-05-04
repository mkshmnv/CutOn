package com.example.cuton.network

import retrofit2.Call
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
    fun getLatestVersion(
        @Query("v") v: String
    ) : Call<VersionModel>

    // TODO post request
    @POST()
    fun asd()

}