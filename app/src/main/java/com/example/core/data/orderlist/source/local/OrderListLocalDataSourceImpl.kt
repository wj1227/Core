package com.example.core.data.orderlist.source.local

import com.example.core.utils.PreferenceManager

class OrderListLocalDataSourceImpl(private val prefs: PreferenceManager) : OrderListLocalDataSource {
    override val email: String
        get() = prefs.email
}