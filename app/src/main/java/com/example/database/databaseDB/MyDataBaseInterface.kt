package com.example.database.databaseDB

import com.example.database.models.MyContact

interface MyDataBaseInterface {

    fun addContact(myContact: MyContact)
    fun getAllContact():List<MyContact>
    fun delateContact(myContact: MyContact)
    fun editContact(myContact: MyContact)
}