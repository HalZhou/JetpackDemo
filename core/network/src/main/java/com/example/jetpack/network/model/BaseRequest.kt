package com.example.jetpack.network.model

class BaseRequest<T>(val data: ArrayList<T>) {
}

fun <T> T.toCreateRequest() = BaseRequest(arrayListOf(this))