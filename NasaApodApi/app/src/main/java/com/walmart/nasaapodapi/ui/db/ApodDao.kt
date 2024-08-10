package com.walmart.nasaapodapi.ui.db


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.walmart.nasaapodapi.data.model.ApodResponse

@Dao
interface ApodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApod(apod: ApodResponse)

    @Query("SELECT * FROM apod_table LIMIT 1")
    suspend fun getLastApod(): ApodResponse?
}

