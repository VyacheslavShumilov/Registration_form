package com.hfad.room.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hfad.room.model.Users

@Database(entities = [Users::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun usersDao():UsersDao
}