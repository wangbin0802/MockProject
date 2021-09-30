package com.stareme.ocbcsimple.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.stareme.ocbcsimple.BaseActivity
import com.stareme.ocbcsimple.adapter.CustomAdapter
import com.stareme.ocbcsimple.databinding.ActivityMainBinding
import com.stareme.ocbcsimple.http.model.Order
import com.stareme.ocbcsimple.ui.transfer.TransferActivity
import com.stareme.ocbcsimple.utils.safeStartActivity

class MainActivity : BaseActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory())
            .get(MainViewModel::class.java)

        mainViewModel.init()

        val balanceText = binding.balanceTv
        val recyclerView = binding.recyclerView
        val makeTransfer = binding.makeTransfer

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        mainViewModel.balance.observe(this, { result ->
                if (result.isSuccess) {
                    balanceText.text = result.getOrNull()
                } else {
                    balanceText.text = "0"
                }
        })

        mainViewModel.transactions.observe(this, { result ->
            if (result.isSuccess) {
                val dataResponse = result.getOrNull()
                if (dataResponse != null) {
                    recyclerView.adapter = CustomAdapter(dataResponse.data)
                }
            }
        })

        makeTransfer.setOnClickListener {
            safeStartActivity(Intent(this, TransferActivity::class.java))
        }
    }
}