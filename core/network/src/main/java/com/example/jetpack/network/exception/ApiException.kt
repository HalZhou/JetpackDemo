package com.example.jetpack.network.exception

import okio.IOException

class ApiException(val code: String?, message: String?) : IOException(message) {

}