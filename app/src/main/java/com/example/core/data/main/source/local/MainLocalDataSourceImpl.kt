package com.example.core.data.main.source.local

import com.example.core.utils.PreferenceManager

class MainLocalDataSourceImpl(private val prefs: PreferenceManager) : MainLocalDataSource {
    override fun logout() = prefs.logout()
}