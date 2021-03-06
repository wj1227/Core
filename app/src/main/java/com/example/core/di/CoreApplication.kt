package com.example.core.di

import android.app.Application
import com.example.core.modules.*
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@CoreApplication)
            modules(
                firebaseModule,
                remoteModule,
                localModule,
                repositoryModule,
                viewModelModule
            )
        }

        Stetho.initializeWithDefaults(this)
    }
}