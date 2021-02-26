package com.example.core.modules

import com.example.core.data.login.source.local.LoginLocalDataSource
import com.example.core.data.login.source.local.LoginLocalDataSourceImpl
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
}