package com.example.falahal_wrold.data.api

import com.example.falahal_wrold.data.model.DisasterEventResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DisasterApiService {

    @GET("events")
    suspend fun getDisasterEvents(
        @Query("status") status: String = "open",
        @Query("limit") limit: Int = 1
    ): DisasterEventResponse
}
