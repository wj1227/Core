package com.example.core.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.core.constants.AUTO_LOGIN
import com.example.core.constants.CORE
import com.example.core.constants.TUTORIAL

class PreferenceManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(CORE, Context.MODE_PRIVATE)
    private val editor = prefs.edit()

    var tutorial: Boolean
        get() = prefs.getBoolean(TUTORIAL, false)
        set(value) = editor.putBoolean(TUTORIAL, value).apply()

    var autoLogin: Boolean
        get() = prefs.getBoolean(AUTO_LOGIN, false)
        set(value) = editor.putBoolean(AUTO_LOGIN, value).apply()
}