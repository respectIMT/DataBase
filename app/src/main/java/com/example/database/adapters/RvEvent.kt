package com.example.database.adapters

import android.view.View
import android.widget.ImageView
import com.example.database.models.MyContact

interface RvEvent {
    fun menuClick(myContact: MyContact, view: ImageView)
}