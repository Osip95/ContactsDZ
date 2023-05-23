package com.example.realmdatabase.changescren


import com.example.realmdatabase.data.ContactRepository
import com.example.realmdatabase.domain.entity.ContactModel
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ChangeContactPresenterTest {
    val id = "777"
    val name = "Vano"
    val surname = "No"
    val number = "12345"
    val newName = "oleg"

    private val repository: ContactRepository = mock()
    private val contact = ContactModel(id,name,surname,number)
    private val presenter = ChangeContactPresenter(repository,contact)

    @Test
    fun `change contact EXPECT repository change contact`(){
        presenter.onChangeContactClicked(name = newName, surname = surname, number = number)
        val updatedContact = ContactModel(id="777",name = newName, surname = surname, number = number)
       verify(repository).changeContact(updatedContact)
    }

    @Test
    fun `change contact EXPECT show change contact success info`() {
        val view: ChangeContactView = mock()

        presenter.initAction(view)

        presenter.onChangeContactClicked(name = newName, surname = surname, number = number)

        verify(view).showChangeContactSuccessInfo()
    }
}