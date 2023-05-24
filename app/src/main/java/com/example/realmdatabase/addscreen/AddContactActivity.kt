package com.example.realmdatabase.addscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.realmdatabase.R
import com.example.realmdatabase.databinding.ActivityAddContactBinding
import com.example.realmdatabase.mainscreen.MainActivity
import org.koin.android.ext.android.inject

class AddContactActivity : AppCompatActivity(), AddContactView {

    private lateinit var binding: ActivityAddContactBinding
    private val addContactPresenter: AddContactPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addContactPresenter.initView(this)

        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etName.requestFocus()

        binding.btnSave.setOnClickListener {
            with(binding) {
                addContactPresenter.addContact(
                    name = etName.text.toString(),
                    surname = etSurname.text.toString(),
                    number = etNumber.text.toString()
                )
            }
        }
    }

    override fun showAddContactSuccessInfo() {
        Toast.makeText(this, getString(R.string.add_contact_message), Toast.LENGTH_SHORT).show()
    }


}