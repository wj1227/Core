package com.example.core.data.selfcall.source

import com.example.core.data.selfcall.SelfCallItem
import com.example.core.data.selfcall.source.local.SelfCallLocalDataSource
import com.example.core.data.selfcall.source.remote.SelfCallRemoteDataSource
import io.reactivex.Completable

class SelfCallRepositoryImpl(
    private val localDataSource: SelfCallLocalDataSource,
    private val remoteDataSource: SelfCallRemoteDataSource
) : SelfCallRepository {
    override val email: String
        get() = localDataSource.email

    override val company: String
        get() = localDataSource.company

    override fun upload(email: String, company: String, item: SelfCallItem): Completable {
        return remoteDataSource.upload(email, company, item)
    }
}