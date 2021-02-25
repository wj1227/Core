package com.example.core.di

import android.app.Application
import com.example.core.modules.localModule
import com.example.core.modules.repository
import com.example.core.modules.viewModelModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CoreApplication)
            modules(
                localModule,
                repository,
                viewModelModule
            )
        }

        Stetho.initializeWithDefaults(this)
    }
}