package com.example.core.utils.databinding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("onClick")
fun View.bindClick(listener: View.OnClickListener) {
    setOnClickListener(listener)
}