package com.stareme.ocbcsimple.data.business

import com.stareme.ocbcsimple.http.RetrofitService
import com.stareme.ocbcsimple.http.model.*
import io.reactivex.Observable

class BusinessDataSource {

    fun fetchBalance(userToken: String): Observable<BalanceResponse> {
        return RetrofitService.getApi().balance(userToken)
    }

    fun fetchTransactions(userToken: String): Observable<TransactionResponse> {
        return RetrofitService.getApi().transactions(userToken)
    }

    fun fetchPayees(userToken: String): Observable<PayeesResponse> {
        return RetrofitService.getApi().payees(userToken)
    }

    fun postTransfer(userToken: String): Observable<TransferResponse> {
        return RetrofitService.getApi().transfer(userToken)
    }
}