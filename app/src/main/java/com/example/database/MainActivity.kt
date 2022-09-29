package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.database.adapters.MyRvAdapter
import com.example.database.adapters.RvEvent
import com.example.database.databaseDB.MyDBHelper
import com.example.database.databinding.ActivityMainBinding
import com.example.database.databinding.ItemDialogBinding
import com.example.database.models.MyContact

class MainActivity : AppCompatActivity(), RvEvent {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myDBHelper: MyDBHelper
    private lateinit var rvAdapter: MyRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDBHelper = MyDBHelper(this)
        rvAdapter = MyRvAdapter(myDBHelper.getAllContact(), this)

        binding.apply {
            rv.adapter = rvAdapter

            btnAdd.setOnClickListener{
                val dialog = AlertDialog.Builder(this@MainActivity).create()
                val itemDialog = ItemDialogBinding.inflate(layoutInflater)
                dialog.setView(itemDialog.root)
                itemDialog.apply {
                    btnSave.setOnClickListener {
                        val myContact = MyContact(
                            edtName.text.toString().trim(),
                            edtNumber.text.toString().trim(),
                        )

                        myDBHelper.addContact(myContact)
                        Toast.makeText(this@MainActivity, "Saqlandi",Toast.LENGTH_SHORT).show()
                        dialog.cancel()
                        rvAdapter.list = myDBHelper.getAllContact()
                        rvAdapter.notifyDataSetChanged()

                    }
                }
                dialog.show()
            }
        }

    }

    override fun menuClick(myContact: MyContact, view: ImageView) {
        val popupMenu = PopupMenu(this,view)
        popupMenu.inflate(R.menu.my_popup_manu)

        popupMenu.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.menu_delate ->{
                    myDBHelper.delateContact(myContact)
                    rvAdapter.list = myDBHelper.getAllContact()
                    rvAdapter.notifyDataSetChanged()
                    Toast.makeText(this, "Delated", Toast.LENGTH_SHORT).show()
                }
                R.id.manu_edit ->{

                    val dialog = AlertDialog.Builder(this).create()
                    val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
                    dialog.setView(itemDialogBinding.root)

                    itemDialogBinding.edtName.setText(myContact.name)
                    itemDialogBinding.edtNumber.setText(myContact.number)

                    itemDialogBinding.btnSave.setOnClickListener {

                        myContact.name = itemDialogBinding.edtName.text.toString().trim()
                        myContact.number = itemDialogBinding.edtNumber.text.toString().trim()

                        myDBHelper.editContact(myContact)
                        rvAdapter.list = myDBHelper.getAllContact()
                        rvAdapter.notifyDataSetChanged()
                        dialog.dismiss()
                        Toast.makeText(this, "Edited", Toast.LENGTH_SHORT).show()
                    }
                    dialog.show()


                }
            }
            true
        }
        popupMenu.show()
    }

}