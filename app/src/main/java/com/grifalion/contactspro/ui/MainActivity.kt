package com.grifalion.contactspro.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.grifalion.contactspro.R
import com.grifalion.contactspro.databinding.ActivityMainBinding
import com.grifalion.contactspro.ui.fragments.MainFragment
import com.grifalion.contactspro.ui.fragments.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val model = ViewModelProvider(this)[MainViewModel::class.java]

        setContentView(binding.root)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container,MainFragment::class.java.newInstance())
                .commit()
    }
}

