package com.example.core.data.splash.source

import com.example.core.data.splash.source.local.SplashLocalDataSource

class SplashRepositoryImpl(
    private val localDataSource: SplashLocalDataSource
) : SplashRepository {
    override val autoLogin: Boolean
        get() = localDataSource.autoLogin
}