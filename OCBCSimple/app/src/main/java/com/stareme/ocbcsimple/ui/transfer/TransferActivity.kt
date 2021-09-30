package com.stareme.ocbcsimple.ui.transfer

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.stareme.ocbcsimple.BaseActivity
import com.stareme.ocbcsimple.databinding.ActivityTransferBinding
import com.stareme.ocbcsimple.http.model.Payee

import com.stareme.ocbcsimple.R


class TransferActivity: BaseActivity() {
    private lateinit var transferViewModel: TransferViewModel
    private lateinit var binding: ActivityTransferBinding

    private lateinit var recipientEdit: EditText

    private var payeeList: ArrayList<Payee>? = null
    private var selectPayee: Payee? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        transferViewModel = ViewModelProvider(this, TransferViewModelFactory())
            .get(TransferViewModel::class.java)
        transferViewModel.init()

        val transferView = binding.submit
        val cancelView = binding.cancel
        val payeeView = binding.payeeIcon
        val pickDateView = binding.dateIcon

        recipientEdit = binding.recipient
        val dateEdit = binding.date
        val description = binding.description
        val amount = binding.amount

        transferViewModel.payeesResult.observe(this, { result ->
            if (result.isSuccess) {
                payeeList = result.getOrNull()
            }
        })

        transferViewModel.transferResult.observe(this, { result ->
            if (result.isSuccess) {
                finish()
            } else {
                val throwable = result.exceptionOrNull()
                showLoginFailed(throwable?.message ?: "unknown error")
            }
        })

        cancelView.setOnClickListener {
            finish()
        }

        transferView.setOnClickListener {
            transferViewModel.transfer(recipientEdit.text.toString(), amount.text.toString().toDouble(),
                dateEdit.text.toString(), description.text.toString())
        }

        payeeView.setOnClickListener {
            showDialog(R.id.dialog_payee)
        }

        pickDateView.setOnClickListener {
            dateEdit.setText("2021-10-12T00:00:00.000Z")
        }
    }

    private fun showLoginFailed(errorString: String) {
        Toast.makeText(this, errorString, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateDialog(id: Int): Dialog? {
        if (payeeList == null)  return null

        val names = arrayOfNulls<String>(payeeList!!.size)
        for (index in payeeList!!.indices) {
            names[index] = payeeList!![index].accountHolderName
        }

        var dialog: AlertDialog? = null
        when (id) {
            R.id.dialog_payee -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(R.string.pick_str)
                    .setItems(names) { _, which ->
                        // The 'which' argument contains the index position
                        // of the selected item
                        selectPayee = payeeList!![which]
                        recipientEdit.setText(names[which])
                    }
                dialog = builder.create()
            }
        }
        return dialog!!
    }
}