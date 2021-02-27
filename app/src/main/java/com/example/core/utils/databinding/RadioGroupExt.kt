package com.example.core.utils.databinding

import android.widget.RadioGroup
import androidx.core.view.children
import androidx.databinding.BindingAdapter

@BindingAdapter("checkWithTag")
fun <T> RadioGroup.bindWithTag(tag: T?) {
    val selectButton = this.children.filter { childButton ->
        childButton.tag == tag
    }.firstOrNull()

    if (selectButton == null) {
        this.clearCheck()
    } else {
        this.check(selectButton.id)
    }
}