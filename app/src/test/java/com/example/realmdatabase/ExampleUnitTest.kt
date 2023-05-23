package com.example.realmdatabase

import com.example.realmdatabase.data.ContactRepositoryImpl
import io.realm.Realm
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun testViewModel() {
        val contactRepository

        val phone = "+88005553535"

        val contact = FakeContact(
            name = "Stas",
            surname = "Qmar",
            phone = "88005553535"
        )

        contactRepository.addContact(contact.name,contact.surname,contact.phone)
        val list = contactRepository.getContact()
        val lastContact = list.last()

        assertEquals(contact, lastContact)
        assertNotEquals(phone, lastContact.number)
    }
}