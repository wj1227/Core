package com.example.core.data.profile.source.local

import com.example.core.data.profile.ProfileUser
import com.example.core.utils.PreferenceManager

class ProfileLocalDataSourceImpl(private val prefs: PreferenceManager) : ProfileLocalDataSource {
    override fun getUser(): ProfileUser {
        return ProfileUser(
            email = prefs.email,
            name = prefs.name,
            company = prefs.company,
            position = prefs.position,
            cellPhone = prefs.cellPhone
        )
    }

    override fun updateUser(user: ProfileUser) = prefs.updateUser(user)
}