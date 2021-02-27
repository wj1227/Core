package com.example.core.data.profile

data class ProfileUser(
    val email: String,
    val name: String,
    var company: String,
    var position: String,
    var cellPhone: String
)