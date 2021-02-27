package com.example.core.data.main.source

import com.example.core.data.main.source.local.MainLocalDataSource

class MainRepositoryImpl(private val localDataSource: MainLocalDataSource) : MainRepository {
    override fun logout() = localDataSource.logout()
}