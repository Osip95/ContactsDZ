package com.example.realmdatabase.mainscreen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.realmdatabase.data.ContactRepository
import com.example.realmdatabase.domain.entity.ContactModel
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.rules.TestRule
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class MainViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    private val repository: ContactRepository = mock()
    private val viewModel = MainViewModel(repository)

    @Test
    fun `edit contact EXPECT go change contact screen event invoked`() {
        val contact = ContactModel(id = "123", name = "Ivan", surname = "", number = "")
        val goChangeContactEventObserver: Observer<ContactModel> = mock()
        viewModel.goChangeContactScreenEvent.observeForever(goChangeContactEventObserver)

        viewModel.editContact(contact)

        verify(goChangeContactEventObserver).onChanged(contact)
    }

    @Test
    fun `all contacts EXPECT get contacts live data invoked`() {
        viewModel.allContacts

        verify(repository).getContactsLiveData()
    }
}