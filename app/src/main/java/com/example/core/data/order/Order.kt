package com.example.core.data.order

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Order(
    val email: String? = null,
    val tag: String? = null,
    val ea: Int? = null,
    val text: String? = null,
    val name: String? = null,
    val company: String? = null,
    val cellPhone: String? = null,
    val date: Long? = null
) : Parcelable