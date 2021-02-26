package com.example.core.modules

import com.example.core.data.signin.source.remote.SigninRemoteDataSource
import com.example.core.data.signin.source.remote.SigninRemoteDataSourceImpl
import org.koin.dsl.module

val remoteModule = module {
    single<SigninRemoteDataSource> {
        SigninRemoteDataSourceImpl(
            get(), get()
        )
    }
}