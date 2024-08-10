package com.walmart.nasaapodapi.data.repository


import com.walmart.nasaapodapi.data.api.ApiService
import com.walmart.nasaapodapi.data.model.ApodResponse
import com.walmart.nasaapodapi.ui.db.ApodDao

class ApodRepository(private val apiService: ApiService, private val apodDao: ApodDao) {

    suspend fun getApod(apiKey: String): ApodResponse? {
        return try {
            val response = apiService.getAstronomyPictureOfTheDay(apiKey)
            if (response.isSuccessful) {
                response.body()?.also {
                    apodDao.insertApod(it)
                }
            } else {
                apodDao.getLastApod()
            }
        } catch (e: Exception) {
            apodDao.getLastApod()
        }
    }
}
