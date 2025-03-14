package com.example.jetpack.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.jetpack.database.entity.CookieEntity

@Dao
interface CookieDao {

    @Query("SELECT * FROM cookies")
    suspend fun getAllCookies(): List<CookieEntity>

    @Delete
    suspend fun deleteCookies(cookies: List<CookieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCookies(cookies: List<CookieEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCookies(cookies: List<CookieEntity>)
}