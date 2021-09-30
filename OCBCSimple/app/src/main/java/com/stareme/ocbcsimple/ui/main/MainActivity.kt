package com.stareme.ocbcsimple.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.stareme.ocbcsimple.BaseActivity
import com.stareme.ocbcsimple.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory())
            .get(MainViewModel::class.java)


    }
}