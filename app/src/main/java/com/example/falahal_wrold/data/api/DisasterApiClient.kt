package com.example.falahal_wrold.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DisasterApiClient {

    private const val BASE_URL = "https://eonet.gsfc.nasa.gov/api/v3/"

    val apiService: DisasterApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DisasterApiService::class.java)
    }
}
