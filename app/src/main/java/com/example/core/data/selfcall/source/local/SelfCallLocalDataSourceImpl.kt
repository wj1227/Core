package com.example.core.data.selfcall.source.local

import com.example.core.utils.PreferenceManager

class SelfCallLocalDataSourceImpl(private val prefs: PreferenceManager) : SelfCallLocalDataSource {
    override val email: String
        get() = prefs.email

    override val company: String
        get() = prefs.company
}