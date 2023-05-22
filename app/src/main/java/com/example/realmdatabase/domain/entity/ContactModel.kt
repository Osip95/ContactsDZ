package com.example.realmdatabase.domain.entity

import java.io.Serializable

data class ContactModel(
    val id: String,
    val name: String,
    val surname: String,
    val number: String,
) : Serializable