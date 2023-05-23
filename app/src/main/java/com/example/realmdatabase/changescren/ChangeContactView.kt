package com.example.realmdatabase.changescren

import com.example.realmdatabase.data.model.Contact
import com.example.realmdatabase.domain.entity.ContactModel

interface ChangeContactView {
    fun showChangeContactSuccessInfo()
    fun prefillContact(contact: ContactModel)
}