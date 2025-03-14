package com.example.jetpack.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cookies")
data class CookieEntity
    (
    @PrimaryKey
    @ColumnInfo("name") val name: String,
    @ColumnInfo("value") val value: String,
    @ColumnInfo("expiresAt") val expiresAt: Long,
    @ColumnInfo("domain") val domain: String,
    @ColumnInfo("path") val path: String,
    @ColumnInfo("secure") val secure: Boolean,
    @ColumnInfo("httpOnly") val httpOnly: Boolean,
    @ColumnInfo("persistent") val persistent: Boolean
)