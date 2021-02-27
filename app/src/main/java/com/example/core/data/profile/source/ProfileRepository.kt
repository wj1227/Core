package com.example.core.data.profile.source

import com.example.core.data.profile.ProfileUser
import io.reactivex.Completable

interface ProfileRepository {
    fun getUser(): ProfileUser
    fun updateUser(user: ProfileUser): Completable
}