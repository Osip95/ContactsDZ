package com.example.realmdatabase.data

import com.example.realmdatabase.data.model.Contact
import com.example.realmdatabase.domain.entity.ContactModel

class ContactConverter {

    fun convert(from: Contact): ContactModel =
        ContactModel(
            id = from.id,
            name = from.name ?: "",
            surname = from.surname ?: "",
            number = from.number ?: "",
        )
}