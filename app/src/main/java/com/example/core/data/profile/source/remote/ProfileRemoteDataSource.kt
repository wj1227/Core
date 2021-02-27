package com.example.core.data.profile.source.remote

import com.example.core.data.profile.ProfileUser
import io.reactivex.Completable

interface ProfileRemoteDataSource {
    fun updateUser(user: ProfileUser): Completable
}