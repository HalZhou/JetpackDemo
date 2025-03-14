package com.example.jetpack.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

val Context.dataStore by preferencesDataStore("user_preferences")

class PreferenceDataStore @Inject constructor(@ApplicationContext private val context: Context) {

    private val dataStore: DataStore<Preferences> = context.dataStore

    suspend fun getString(key: String): Flow<String> {
        val stringKey = stringPreferencesKey(key)
        return dataStore.data.mapNotNull { it[stringKey] }
    }

    suspend fun saveString(key: String, value: String): Preferences {
        val stringKey = stringPreferencesKey(key)
        val preference = dataStore.edit {
            it[stringKey] = value
        }
        return preference
    }

    suspend fun getInt(key: String): Flow<Int> {
        val intKey = intPreferencesKey(key)
        return dataStore.data.mapNotNull { it[intKey] }
    }

    suspend fun saveInt(key: String, value: Int): Preferences {
        val intKey = intPreferencesKey(key)
        return dataStore.edit { it[intKey] = value }
    }

    suspend fun getBoolean(key: String): Flow<Boolean> {
        val booleanKey = booleanPreferencesKey(key)
        return dataStore.data.mapNotNull { it[booleanKey] }
    }

    suspend fun saveBoolean(key: String, value: Boolean): Preferences {
        val booleanKey = booleanPreferencesKey(key)
        return dataStore.edit { it[booleanKey] = value }
    }

    suspend fun getDouble(key: String): Flow<Double> {
        val doubleKey = doublePreferencesKey(key)
        return dataStore.data.mapNotNull { it[doubleKey] }
    }

    suspend fun saveDouble(key: String, value: Double): Preferences {
        val doubleKey = doublePreferencesKey(key)
        return dataStore.edit { it[doubleKey] = value }
    }

    suspend fun getFloat(key: String): Flow<Float> {
        val floatKey = floatPreferencesKey(key)
        return dataStore.data.mapNotNull { it[floatKey] }
    }

    suspend fun saveFloat(key: String, value: Float): Preferences {
        val floatKey = floatPreferencesKey(key)
        return dataStore.edit { it[floatKey] = value }
    }

}