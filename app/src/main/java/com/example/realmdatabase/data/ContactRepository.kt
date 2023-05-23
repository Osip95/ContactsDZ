package com.example.realmdatabase.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.realmdatabase.data.model.Contact
import com.example.realmdatabase.domain.entity.ContactModel
import io.realm.Realm
import java.util.UUID

interface ContactRepository {

    fun addContact(name: String, surname: String, number: String)

    fun getContactsLiveData(): LiveData<List<ContactModel>>

    fun changeContact(contact: ContactModel)
}