package com.example.database.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.database.databinding.ItemRvBinding
import com.example.database.models.MyContact

class MyRvAdapter(var list:List<MyContact> = emptyList(), val rvEvent: RvEvent) : RecyclerView.Adapter<MyRvAdapter.Vh>() {

    inner class Vh(val itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root){

        fun onBind(myContact:MyContact, position: Int){
            itemRvBinding.tvId.text = myContact.id.toString()
            itemRvBinding.tvName.text = myContact.name
            itemRvBinding.tvNumber.text  = myContact.number
            itemRvBinding.manuImage.setOnClickListener {
                rvEvent.menuClick(myContact, itemRvBinding.manuImage)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)

    }

    override fun getItemCount(): Int {
        return list.size

    }
}
