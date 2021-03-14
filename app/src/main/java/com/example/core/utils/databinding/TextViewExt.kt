package com.example.core.utils.databinding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("convertToDate")
fun TextView.longToString(time: Long?) {
    if (time == null) {
        text = "알 수 없음"
    } else {
        val localDate = Date(time)
        val dt = SimpleDateFormat("yy년 MM월 dd일")
        val tz = TimeZone.getTimeZone("Asia/Seoul")
        dt.timeZone = tz

        text = dt.format(localDate)
    }
}

@BindingAdapter("bindText")
fun TextView.bindText(hope: String?) {
    text = hope ?: "없음"
}
