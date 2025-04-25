package com.example.jetpack.network.model

class ConfirmationDto(
    val code: String?,
    val message: String?,
    val type: String?,
    val description: String?,
    var confirmedOption: ConfirmationOptionDto? = null,
    val options: MutableList<ConfirmationOptionDto> = arrayListOf()
)

data class ConfirmationOptionDto(
    val code: String? = null,
    val message: String? = null,
    val primary: Boolean? = null
)