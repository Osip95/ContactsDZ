package com.example.realmdatabase.data.model

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class Contact() : RealmModel, Parcelable {
    @PrimaryKey
    var id: String = ""

    @Required
    var name: String? = ""

    @Required
    var surname: String? = ""

    @Required
    var number: String? = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readString() ?: ""
        name = parcel.readString()
        surname = parcel.readString()
        number = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(number)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contact> {
        override fun createFromParcel(parcel: Parcel): Contact {
            return Contact(parcel)
        }

        override fun newArray(size: Int): Array<Contact?> {
            return arrayOfNulls(size)
        }
    }
}