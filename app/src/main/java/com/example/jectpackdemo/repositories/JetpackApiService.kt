package com.example.jectpackdemo.repositories

import retrofit2.http.Body
import retrofit2.http.POST

interface JetpackApiService {

    @POST("")
    suspend fun login(@Body request : String) : Long
}