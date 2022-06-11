package com.hfad.room

import android.app.Application
import androidx.room.Room
import com.hfad.room.dao.AppDatabase

class App : Application() {

    private lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(applicationContext,
            AppDatabase::class.java,
        "users_database").build()
    }

    fun getDatabase():AppDatabase{
        return database
    }
}