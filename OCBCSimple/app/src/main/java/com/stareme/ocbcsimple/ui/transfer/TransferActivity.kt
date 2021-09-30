package com.stareme.ocbcsimple.ui.transfer

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.stareme.ocbcsimple.BaseActivity
import com.stareme.ocbcsimple.databinding.ActivityTransferBinding

class TransferActivity: BaseActivity() {
    private lateinit var transferViewModel: TransferViewModel
    private lateinit var binding: ActivityTransferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        transferViewModel = ViewModelProvider(this, TransferViewModelFactory())
            .get(TransferViewModel::class.java)
    }
}