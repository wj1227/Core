package com.example.core.data.order

data class Order(
    val tag: String,
    val ea: Int,
    val text: String,
    val name: String,
    val company: String,
    val cellPhone: String,
    val date: Long
)