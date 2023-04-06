package com.grifalion.contactspro.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grifalion.contactspro.model.Contact

class MainViewModel: ViewModel() {
    val detailData = MutableLiveData<Contact>()

}