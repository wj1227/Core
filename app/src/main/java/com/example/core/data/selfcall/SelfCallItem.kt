package com.example.core.data.selfcall

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SelfCallItem(
    val email: String? = null,
    val tag: String? = null,
    val work: String? = null,
    val salary: String? = null,
    val year: String? = null,
    val hope: String? = null,
    val date: Long? = null
) : Parcelable