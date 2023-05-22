package com.example.realmdatabase.changescren

import com.example.realmdatabase.data.ContactRepository
import com.example.realmdatabase.data.model.Contact
import com.example.realmdatabase.domain.entity.ContactModel

class ChangeContactPresenter(
    private val contactRepository: ContactRepository,
    private val contact: ContactModel
) {

    private var changeContactView: ChangeContactView? = null

    fun initAction(changeContactView: ChangeContactView) {
        this.changeContactView = changeContactView
        changeContactView.prefillContact(contact)
    }

    fun onChangeContactClicked(name: String, surname: String, number: String) {
        if (contact.name != name || contact.surname != surname || contact.number != number) {
            val updatedContact = contact.copy(name = name, surname = surname, number = number)
            contactRepository.changeContact(updatedContact)
            changeContactView?.showChangeContactSuccessInfo()
        }
    }
}