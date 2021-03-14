package com.example.core.data.signin

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SigninUser(
    val email: String,
    val name: String,
    val company: String,
    val position: String,
    val cellPhone: String
) : Parcelable