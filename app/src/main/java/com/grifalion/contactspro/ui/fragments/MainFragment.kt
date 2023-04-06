package com.grifalion.contactspro.ui.fragments

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.grifalion.contactspro.R
import com.grifalion.contactspro.adapter.ContactAdapter
import com.grifalion.contactspro.databinding.FragmentMainBinding
import com.grifalion.contactspro.model.Contact
import java.util.*
import kotlin.collections.ArrayList

class MainFragment: Fragment(), ContactAdapter.Listener{
    private lateinit var binding: FragmentMainBinding
    private val adapter = ContactAdapter(this)
    private val list = ListContacts()
    private val vm: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                fitlerList(newText)
                return true
            }

        })
    }

    private fun fitlerList(query: String?){
        if(query != null){
            val fitleredList = ArrayList<Contact>()
            for(i in list.listContacts){
                if(i.firstName.lowercase(Locale.ROOT).trim().contains(query) || i.lastName.lowercase(Locale.ROOT).trim().contains(query)){
                    fitleredList.add(i)
                } else if(i.firstName.contains(query) || i.lastName.contains(query)){
                    fitleredList.add(i)
                }
            }
            if(fitleredList.isEmpty()){
                Toast.makeText(requireContext(),getString(R.string.info_error),Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(fitleredList)
            }
        }
    }

    private fun initRcView() = with(binding){
        var dividerItemDecoration = DividerItemDecoration(requireContext(),RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources,R.drawable.decoration,null)?.let {
            dividerItemDecoration.setDrawable(it)
        }
        val recyclerView = view?.findViewById<RecyclerView>(R.id.rcView)
        recyclerView?.addItemDecoration(dividerItemDecoration)

        rcView.adapter = adapter
        adapter.setContact(list.listContacts)

    }

    override fun onClick(contact: Contact) {
        vm.detailData.value = contact
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        fragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,DetailFragment::class.java.newInstance())
            .addToBackStack("contacts")
            .commit()
    }

    override fun onLongListener(contact: Contact) {
        adapter.removeContact(contact)
    }
}