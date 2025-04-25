package com.example.jetpack.network.model

class BaseResponse<T>(
    val data: ArrayList<T>,
    val confirmations: MutableList<ConfirmationDto> = arrayListOf(),
    val errors: MutableList<ErrorDto> = arrayListOf(),
    val warnings: MutableList<WarningDto> = arrayListOf()
) {
}