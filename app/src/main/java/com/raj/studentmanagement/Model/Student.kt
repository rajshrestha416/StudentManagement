package com.raj.studentmanagement.Model

import android.os.Parcel
import android.os.Parcelable

data class Student(val studentName:String ?= null,
                   val studentAge:Int ?= null,
                   val studentGender:String ?= null,
                   val studentAddress:String ?= null,
                   val profileImage:String ?= null):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(studentName)
        parcel.writeValue(studentAge)
        parcel.writeString(studentAddress)
        parcel.writeString(studentGender)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }
}