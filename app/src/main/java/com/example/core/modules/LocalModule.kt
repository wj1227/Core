package com.example.core.modules

import com.example.core.data.login.source.local.LoginLocalDataSource
import com.example.core.data.login.source.local.LoginLocalDataSourceImpl
import com.example.core.data.main.source.local.MainLocalDataSource
import com.example.core.data.main.source.local.MainLocalDataSourceImpl
import com.example.core.data.profile.source.local.ProfileLocalDataSource
import com.example.core.data.profile.source.local.ProfileLocalDataSourceImpl
import com.example.core.data.selfcall.source.local.SelfCallLocalDataSource
import com.example.core.data.selfcall.source.local.SelfCallLocalDataSourceImpl
import com.example.core.data.signin.source.local.SigninLocalDataSource
import com.example.core.data.signin.source.local.SigninLocalDataSourceImpl
import com.example.core.data.suggestion.source.local.SuggestionLocalDataSource
import com.example.core.data.suggestion.source.local.SuggestionLocalDataSourceImpl
import com.example.core.data.tutorial.source.local.TutorialLocalDataSource
import com.example.core.data.tutorial.source.local.TutorialLocalDataSourceImpl
import com.example.core.utils.PreferenceManager
import org.koin.dsl.module

val localModule = module {
    single { PreferenceManager(get()) }

    single<TutorialLocalDataSource> {
        TutorialLocalDataSourceImpl(
            get()
        )
    }

    single<LoginLocalDataSource> {
        LoginLocalDataSourceImpl(
            get()
        )
    }

    single<MainLocalDataSource> {
        MainLocalDataSourceImpl(
            get()
        )
    }

    single<SigninLocalDataSource> {
        SigninLocalDataSourceImpl(
            get()
        )
    }

    single<ProfileLocalDataSource> {
        ProfileLocalDataSourceImpl(
            get()
        )
    }

    single<SelfCallLocalDataSource> {
        SelfCallLocalDataSourceImpl(
            get()
        )
    }

    single<SuggestionLocalDataSource> {
        SuggestionLocalDataSourceImpl(
            get()
        )
    }
}