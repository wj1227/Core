package com.example.core.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.core.constants.*
import com.example.core.data.profile.ProfileUser

class PreferenceManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(CORE, Context.MODE_PRIVATE)
    private val editor = prefs.edit()

    var tutorial: Boolean
        get() = prefs.getBoolean(TUTORIAL, false)
        set(value) = editor.putBoolean(TUTORIAL, value).apply()

    var autoLogin: Boolean
        get() = prefs.getBoolean(AUTO_LOGIN, false)
        set(value) = editor.putBoolean(AUTO_LOGIN, value).apply()

    var email: String
        get() = prefs.getString(EMAIL, "") ?: ""
        set(value) = editor.putString(EMAIL, value).apply()

    var name: String
        get() = prefs.getString(NAME, "") ?: ""
        set(value) = editor.putString(NAME, value).apply()

    var company: String
        get() = prefs.getString(COMPANY, "") ?: ""
        set(value) = editor.putString(COMPANY, value).apply()

    var position: String
        get() = prefs.getString(POSITION, "") ?: ""
        set(value) = editor.putString(POSITION, value).apply()

    var cellPhone: String
        get() = prefs.getString(CELL_PHONE, "") ?: ""
        set(value) = editor.putString(CELL_PHONE, value).apply()

    fun logout() {
        editor.remove(AUTO_LOGIN).apply()
    }

    fun updateUser(user: ProfileUser) = with(editor) {
        putString(COMPANY, user.company).apply()
        putString(POSITION, user.position).apply()
        putString(CELL_PHONE, user.cellPhone).apply()
    }

}