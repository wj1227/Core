package com.example.core.modules

import com.example.core.data.tutorial.local.TutorialLocalDataSource
import com.example.core.data.tutorial.local.TutorialLocalDataSourceImpl
import com.example.core.utils.PreferenceManager
import org.koin.dsl.module

val localModule = module {
    single { PreferenceManager(get()) }
    single<TutorialLocalDataSource> { TutorialLocalDataSourceImpl(get()) }
}