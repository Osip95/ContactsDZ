package com.example.realmdatabase

import android.app.Application
import com.example.realmdatabase.di.appModule
import com.example.realmdatabase.di.instrumentedTestRealmConfigModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TestApplication)
            modules(appModule, instrumentedTestRealmConfigModule)
        }
    }
}