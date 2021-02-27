package com.example.core.data.profile.source.local

import com.example.core.data.profile.ProfileUser

interface ProfileLocalDataSource {
    fun getUser(): ProfileUser
    fun updateUser(user: ProfileUser)
}