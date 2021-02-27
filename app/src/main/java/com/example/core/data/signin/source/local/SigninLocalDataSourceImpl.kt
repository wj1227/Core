package com.example.core.data.signin.source.local

import com.example.core.data.signin.SigninUser
import com.example.core.utils.PreferenceManager

class SigninLocalDataSourceImpl(private val prefs: PreferenceManager) : SigninLocalDataSource {
    override fun saveUser(user: SigninUser) {
        localSaveUser(user)
    }

    private fun localSaveUser(user: SigninUser) =
        with(prefs) {
            email = user.email
            name = user.name
            company = user.company
            position = user.position
            cellPhone = user.cellPhone
        }

}