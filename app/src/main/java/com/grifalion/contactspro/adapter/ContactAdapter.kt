package com.grifalion.contactspro.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grifalion.contactspro.databinding.ItemListBinding
import com.grifalion.contactspro.model.Contact
import com.grifalion.contactspro.ui.fragments.DetailFragment

class ContactAdapter(val listener: Listener): RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private var listContacts = ArrayList<Contact>()



    class ContactViewHolder(var binding: ItemListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ItemListBinding
                .inflate(LayoutInflater
                    .from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = listContacts.size


    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = listContacts[position]
        holder.binding.tvFirstName.text = item.firstName
        holder.binding.tvLastName.text = item.lastName
        holder.binding.tvPhoneNumber.text = item.numberPhone
        Glide.with(holder.binding.imView)
            .load(item.imIcon)
            .into(holder.binding.imView)

        holder.itemView.rootView.setOnClickListener{
            listener.onClick(item)

        }
        holder.itemView.setOnLongClickListener {
            listener.onLongListener(item)
        return@setOnLongClickListener true
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setContact(contacts: List<Contact>){
        listContacts.addAll(contacts)
        notifyDataSetChanged()
    }

    fun setFilteredList(contacts: List<Contact>){
        listContacts = contacts as ArrayList<Contact>
        notifyDataSetChanged()
    }

    fun removeContact(contact: Contact){
        listContacts.remove(contact)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClick(contact: Contact)
        fun onLongListener(contact: Contact)
    }

}


