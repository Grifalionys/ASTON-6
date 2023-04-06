package com.grifalion.contactspro.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.grifalion.contactspro.adapter.ContactAdapter
import com.grifalion.contactspro.databinding.FragmentDetailBinding
import com.grifalion.contactspro.model.Contact

class DetailFragment: Fragment(){
    private lateinit var binding: FragmentDetailBinding
    private val vm: MainViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.detailData.observe(viewLifecycleOwner){
            Glide.with(binding.imIconD)
                .load(it.imIcon)
                .into(binding.imIconD)
            binding.tvFirstNameD.text = it.firstName
            binding.tvLastNameD.text = it.lastName
            binding.tvNumberPhoneD.text = it.numberPhone
        }
    }


}