package com.stareme.ocbcsimple.ui.transfer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stareme.ocbcsimple.data.business.BusinessRepository
import com.stareme.ocbcsimple.http.model.Payee
import com.stareme.ocbcsimple.http.model.TransferBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.lang.Exception

class TransferViewModel(private val businessRepository: BusinessRepository): ViewModel() {
    val payeesResult = MutableLiveData<Result<ArrayList<Payee>>>()
    val transferResult = MutableLiveData<Result<Boolean>>()

    private val composites = CompositeDisposable()

    fun init() {
        businessRepository.fetchPayees()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ payeesRes ->
                if (payeesRes.success()) {
                    payeesResult.value = Result.success(payeesRes.data!!)
                } else {
                    payeesResult.value = Result.failure(Exception(payeesRes.description))
                }
            }, {
                payeesResult.value = Result.failure(it)
            }).also {
                composites.add(it)
            }
    }

    fun transfer(recipientAccountNo: String, amount: Double, date: String, des: String) {
        businessRepository.postTransfer(TransferBody(recipientAccountNo, amount, date, des))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ transferRes ->
                if (transferRes.success()) {
                    transferResult.value = Result.success(true)
                } else {
                    transferResult.value = Result.failure(Exception(transferRes.description))
                }
            }, {
                transferResult.value = Result.failure(it)
            }).also {
                composites.add(it)
            }
    }

    override fun onCleared() {
        super.onCleared()
        composites.clear()
    }
}