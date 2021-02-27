package com.example.core.modules

import com.example.core.data.login.source.LoginRepository
import com.example.core.data.login.source.LoginRepositoryImpl
import com.example.core.data.main.source.MainRepository
import com.example.core.data.main.source.MainRepositoryImpl
import com.example.core.data.profile.source.ProfileRepository
import com.example.core.data.profile.source.ProfileRepositoryImpl
import com.example.core.data.selfcall.source.SelfCallRepository
import com.example.core.data.selfcall.source.SelfCallRepositoryImpl
import com.example.core.data.signin.source.SigninRepository
import com.example.core.data.signin.source.SigninRepositoryImpl
import com.example.core.data.tutorial.source.TutorialRepository
import com.example.core.data.tutorial.source.TutorialRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<TutorialRepository> {
        TutorialRepositoryImpl(
            get()
        )
    }

    single<SigninRepository> {
        SigninRepositoryImpl(
            get(), get()
        )
    }

    single<LoginRepository> {
        LoginRepositoryImpl(
            get(), get()
        )
    }

    single<MainRepository> {
        MainRepositoryImpl(
            get()
        )
    }

    single<ProfileRepository> {
        ProfileRepositoryImpl(
            get(), get()
        )
    }

    single<SelfCallRepository> {
        SelfCallRepositoryImpl(
            get(), get()
        )
    }
}