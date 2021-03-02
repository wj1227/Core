package com.example.core.data.order.source.local

import com.example.core.utils.PreferenceManager

class OrderLocalDataSourceImpl(private val prefs: PreferenceManager) : OrderLocalDataSource {
    override val name: String
        get() = prefs.name

    override val company: String
        get() = prefs.company

    override val cellPhone: String
        get() = prefs.cellPhone

    override val email: String
        get() = prefs.email
}