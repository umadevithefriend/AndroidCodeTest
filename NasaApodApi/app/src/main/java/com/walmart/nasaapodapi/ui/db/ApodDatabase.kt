package com.walmart.nasaapodapi.ui.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.walmart.nasaapodapi.data.model.ApodResponse

@Database(entities = [ApodResponse::class], version = 1)
abstract class ApodDatabase : RoomDatabase() {
    abstract fun apodDao(): ApodDao

    companion object {
        @Volatile private var instance: ApodDatabase? = null

        fun getDatabase(context: Context): ApodDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    ApodDatabase::class.java, "apod_database"
                ).build().also { instance = it }
            }
    }
}
