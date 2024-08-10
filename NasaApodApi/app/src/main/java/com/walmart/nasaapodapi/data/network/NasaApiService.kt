package com.walmart.nasaapodapi.data.network


import com.walmart.nasaapodapi.data.model.ApodResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {
    @GET("planetary/apod")
    suspend fun getAstronomyPictureOfTheDay(
        @Query("api_key") apiKey: String
    ): Response<ApodResponse>
}
