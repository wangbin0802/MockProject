package com.stareme.ocbcsimple.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stareme.ocbcsimple.data.business.BusinessRepository
import com.stareme.ocbcsimple.http.model.TransactionResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.lang.Exception

class MainViewModel(private val businessRepository: BusinessRepository): ViewModel() {

    val balance = MutableLiveData<Result<String>>()
    val transactions = MutableLiveData<Result<TransactionResponse>>()

    private val composites = CompositeDisposable()

    fun init() {
        businessRepository.fetchBalance()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ balanceRes ->
                if (balanceRes.success()) {
                    balance.value = Result.success("SGD ${balanceRes.balance}")
                }
            }, {
                balance.value = Result.failure(it)
            }).also {
                composites.add(it)
            }

        businessRepository.fetchTransactions()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ transactionRes ->
                if (transactionRes.success()) {
                    transactions.value = Result.success(transactionRes)
                } else {
                    transactions.value = Result.failure(Exception("fail"))
                }
            }, {
                transactions.value = Result.failure(it)
            }).also {
                composites.add(it)
            }
    }

    override fun onCleared() {
        super.onCleared()
        composites.clear()
    }
}