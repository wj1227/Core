package com.example.core.data.splash.source.local

import com.example.core.utils.PreferenceManager

class SplashLocalDataSourceImpl(private val prefs: PreferenceManager) : SplashLocalDataSource {
    override val autoLogin: Boolean
        get() = prefs.autoLogin
}