package com.example.realmdatabase

import android.app.Application
import com.example.realmdatabase.di.appModule
import com.example.realmdatabase.di.productionRealmConfigModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule, productionRealmConfigModule)
        }
    }
}