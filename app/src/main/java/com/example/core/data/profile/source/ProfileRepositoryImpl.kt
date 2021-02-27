package com.example.core.data.profile.source

import com.example.core.data.profile.ProfileUser
import com.example.core.data.profile.source.local.ProfileLocalDataSource
import com.example.core.data.profile.source.remote.ProfileRemoteDataSource
import io.reactivex.Completable

class ProfileRepositoryImpl(
    private val localDataSource: ProfileLocalDataSource,
    private val remoteDataSource: ProfileRemoteDataSource
) : ProfileRepository {
    override fun getUser() = localDataSource.getUser()

    override fun updateUser(user: ProfileUser): Completable {
        return remoteDataSource.updateUser(user)
            .doOnComplete { localDataSource.updateUser(user) }
    }
}