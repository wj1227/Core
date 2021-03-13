package com.example.core.data.suggestion

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SuggestionItem(
    val email: String? = null,
    val name: String? = null,
    val text: String? = null,
    val date: Long? = null
) : Parcelable