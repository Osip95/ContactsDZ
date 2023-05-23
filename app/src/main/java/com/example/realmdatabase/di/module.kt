package com.example.realmdatabase.di

import com.example.realmdatabase.changescren.ChangeContactPresenter
import com.example.realmdatabase.data.ContactRepository
import com.example.realmdatabase.data.ContactRepositoryImpl
import com.example.realmdatabase.mainscreen.MainViewModel
import com.example.realmdatabase.addscreen.AddContactPresenter
import com.example.realmdatabase.data.ContactConverter
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<Realm> {
        Realm.init(androidApplication())
        Realm.setDefaultConfiguration(get())
        Realm.getDefaultInstance()
    }

    single { ContactConverter() }

    single<ContactRepository> {
        ContactRepositoryImpl(realm = get(), contactConverter = get())
    }



    viewModel {
        MainViewModel(contactRepository = get())
    }

    factory {
        AddContactPresenter(contactRepository = get())
    }

    factory { params ->
        ChangeContactPresenter(contactRepository = get(), contact = params[0])
    }


}