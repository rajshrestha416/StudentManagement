package com.raj.studentmanagement.Model

import android.os.Parcel
import android.os.Parcelable

data class Admin(
    val firstName: String? =null,
    val lastName: String? =null,
    val username:String? =null,
    val password: String? =null,
    val oldPassword: String? =null
    ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeString(oldPassword)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Admin> {
        override fun createFromParcel(parcel: Parcel): Admin {
            return Admin(parcel)
        }

        override fun newArray(size: Int): Array<Admin?> {
            return arrayOfNulls(size)
        }
    }
}