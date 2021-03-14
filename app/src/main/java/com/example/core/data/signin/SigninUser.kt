package com.example.core.data.signin

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SigninUser(
    val email: String? = null,
    val name: String? = null,
    val company: String? = null,
    val position: String? = null,
    val cellPhone: String? = null
) : Parcelable