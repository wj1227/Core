package com.example.core.modules

import com.example.core.data.login.source.remote.LoginRemoteDataSource
import com.example.core.data.login.source.remote.LoginRemoteDataSourceImpl
import com.example.core.data.profile.source.remote.ProfileRemoteDataSource
import com.example.core.data.profile.source.remote.ProfileRemoteDataSourceImpl
import com.example.core.data.selfcall.source.remote.SelfCallRemoteDataSource
import com.example.core.data.selfcall.source.remote.SelfCallRemoteDataSourceImpl
import com.example.core.data.signin.source.remote.SigninRemoteDataSource
import com.example.core.data.signin.source.remote.SigninRemoteDataSourceImpl
import org.koin.dsl.module

val remoteModule = module {
    single<SigninRemoteDataSource> {
        SigninRemoteDataSourceImpl(
            get(), get()
        )
    }

    single<LoginRemoteDataSource> {
        LoginRemoteDataSourceImpl(
            get()
        )
    }

    single<ProfileRemoteDataSource> {
        ProfileRemoteDataSourceImpl(
            get()
        )
    }

    single<SelfCallRemoteDataSource> {
        SelfCallRemoteDataSourceImpl(
            get()
        )
    }
}