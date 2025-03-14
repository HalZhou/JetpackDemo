package com.example.jetpack.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpack.database.dao.CookieDao
import com.example.jetpack.database.entity.CookieEntity

@Database(entities = [CookieEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cookieDao() : CookieDao
}