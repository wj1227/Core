package com.example.core.data.login.source.local

import com.example.core.utils.PreferenceManager

class LoginLocalDataSourceImpl(private val prefs: PreferenceManager) : LoginLocalDataSource {
    override var autoLogin: Boolean
        get() = prefs.autoLogin
        set(value) {
            prefs.autoLogin = value
        }
}