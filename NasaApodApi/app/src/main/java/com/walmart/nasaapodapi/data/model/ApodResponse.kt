package com.walmart.nasaapodapi.data.model



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apod_table")
data class ApodResponse(
    @PrimaryKey val date: String,
    val title: String,
    val explanation: String,
    val url: String
)

