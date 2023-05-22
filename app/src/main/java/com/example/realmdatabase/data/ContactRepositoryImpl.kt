package com.example.realmdatabase.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.realmdatabase.data.model.Contact
import com.example.realmdatabase.domain.entity.ContactModel
import io.realm.Realm
import java.util.*

class ContactRepositoryImpl(
    private val realm: Realm,
    private val contactConverter: ContactConverter,
) : ContactRepository {

    private val contacts = MutableLiveData(getContacts())

    init {
        realm.addChangeListener {
            contacts.value = getContacts()
        }
    }

    private fun getContacts(): List<ContactModel> =
        realm.where(Contact::class.java).findAll().map(contactConverter::convert)

    override fun addContact(name: String, surname: String, number: String) {
        realm.executeTransaction {
            it.createObject(Contact::class.java, UUID.randomUUID().toString()).apply {
                this.name = name
                this.surname = surname
                this.number = number
            }
        }
    }

    override fun getContactsLiveData(): LiveData<List<ContactModel>> =
        contacts

    override fun changeContact(
        contact: ContactModel
    ) {
        realm.executeTransaction {
            val contactFromDatabase: Contact? =
                it.where(Contact::class.java).equalTo("id", contact.id).findFirst()

            contactFromDatabase?.name = contact.name
            contactFromDatabase?.surname = contact.surname
            contactFromDatabase?.number = contact.number
        }
    }

}