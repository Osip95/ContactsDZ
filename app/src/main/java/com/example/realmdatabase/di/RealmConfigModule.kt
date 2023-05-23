package com.example.realmdatabase.di

import io.realm.RealmConfiguration
import org.koin.dsl.module

val productionRealmConfigModule = module {
    single {
        RealmConfiguration.Builder()
            .name("todo.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()
    }
}

val instrumentedTestRealmConfigModule = module {
    single {
        RealmConfiguration.Builder()
            .inMemory()
            .name("test-realm")
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()
    }
}