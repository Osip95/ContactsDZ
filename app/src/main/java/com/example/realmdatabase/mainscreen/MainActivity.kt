package com.example.realmdatabase.mainscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import com.example.realmdatabase.addscreen.AddContactActivity
import com.example.realmdatabase.changescren.ChangeContactActivity
import com.example.realmdatabase.data.model.Contact
import com.example.realmdatabase.databinding.ActivityMainBinding
import com.example.realmdatabase.domain.entity.ContactModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), LifecycleObserver {

     lateinit var binding: ActivityMainBinding

    private val defaultLifecycleObserver = object : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
            Log.d("Main", "DefaultLifecycleObserver - onCreate")
        }

        override fun onStart(owner: LifecycleOwner) {
            super.onStart(owner)
            Log.d("Main", "DefaultLifecycleObserver - onStart")
        }

        override fun onResume(owner: LifecycleOwner) {
            super.onResume(owner)
            Log.d("Main", "DefaultLifecycleObserver - onResume")
        }
    }

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(defaultLifecycleObserver)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ContactsAdapter{ contact ->
            viewModel.editContact(contact)
        }
        viewModel.allContacts.observe(this) {
            adapter.setData(it)
        }
        viewModel.goChangeContactScreenEvent.observe(this, ::startChangeContactActivity)

        binding.rvContacts.adapter = adapter

        binding.fabAddContact.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivity(intent)
        }
    }

    private fun startChangeContactActivity(contact: ContactModel) {
        startActivity(
            ChangeContactActivity.createIntent(
                this,
                contact
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(defaultLifecycleObserver)
    }


}