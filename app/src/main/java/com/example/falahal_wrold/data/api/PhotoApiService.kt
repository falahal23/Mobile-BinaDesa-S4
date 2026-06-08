package com.example.falahal_wrold.data.api

import com.example.falahal_wrold.data.model.PhotoModel
import retrofit2.http.GET

interface PhotoApiService {
    @GET("list")
    suspend fun getPhotos(): List<PhotoModel>
}